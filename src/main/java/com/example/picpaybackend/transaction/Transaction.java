package com.example.picpaybackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRANSACTIONS")
public class Transaction {
        @Id 
        private Long id;
        private Long payer;
        private Long payee;
        private BigDecimal value;
        
        @CreatedDate 
        private LocalDateTime createdAt; 
        
        public Transaction (Long id, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
        	this.id = id;
        	this.payer = payer;
        	this.payee = payee;
        	this.value = value.setScale(2);
        	this.createdAt = createdAt;
        }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getPayer() {
			return payer;
		}

		public void setPayer(Long payer) {
			this.payer = payer;
		}

		public Long getPayee() {
			return payee;
		}

		public void setPayee(Long payee) {
			this.payee = payee;
		}

		public BigDecimal getValue() {
			return value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
        
        

}