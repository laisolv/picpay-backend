package com.example.picpaybackend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.picpaybackend.transaction.Transaction;

@Service
	public class DebitService {
	
	private final TransactionService transactionService; 
	
	@Autowired
	public DebitService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	public void debitFixed(Long payerId, Long payeeId, BigDecimal fixedAmount) {
				
		Transaction transaction = new Transaction(null, payerId, payeeId, fixedAmount, LocalDateTime.now());
		
		transactionService.create(transaction);
	
		
	}
}