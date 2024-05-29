package com.example.picpaybackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.picpaybackend.authorization.AuthorizerService;
import com.example.picpaybackend.notification.NotificationService;
import com.example.picpaybackend.transaction.InvalidTransactionException;
import com.example.picpaybackend.transaction.Transaction;
import com.example.picpaybackend.transaction.TransactionRepository;
import com.example.picpaybackend.wallet.Wallet;
import com.example.picpaybackend.wallet.WalletRepository;
import com.example.picpaybackend.wallet.WalletType;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,
            AuthorizerService authorizerService, NotificationService notificationService) {

        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    public Transaction create(Transaction transaction) {
        // 1 - validar
        validate(transaction);

        // 2 - criar a transação
        var newTransaction = transactionRepository.save(transaction);

        // 3 - debitar da carteira
        var walletPayer = walletRepository.findById(transaction.getPayer()).get();
        var walletPayee = walletRepository.findById(transaction.getPayee()).get();
        walletRepository.save(walletPayer.debit(transaction.getValue()));
        walletRepository.save(walletPayee.credit(transaction.getValue()));
        

        // 4 - chamar serviços externos
        // authorize transaction
        authorizerService.authorize(transaction);

        // notificação
        notificationService.notify(transaction);

        return newTransaction;
    }

    /*
     * se pagador tem uma carteira do tipo comum
     * se o pagador tem saldo suficiente
     * o pagador não pode ser o recebedor
     */

    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.getPayee())
                .map(payee -> walletRepository.findById(transaction.getPayer())
                        .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid transaction - %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid transaction - %s".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() &&
                payer.balance().compareTo(transaction.getValue()) >= 0 &&
                !payer.id().equals(transaction.getPayee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
    
 }

