package com.example.picpaybackend.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.picpaybackend.service.DebitService;
import com.example.picpaybackend.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final DebitService debitService;

    public TransactionController(TransactionService transactionService, DebitService debitService) {
        this.transactionService = transactionService;
        this.debitService = debitService;
    }

    @GetMapping
    public List<Transaction> list() {
        return transactionService.list();
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }
    

}
