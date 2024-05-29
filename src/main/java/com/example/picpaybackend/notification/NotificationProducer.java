package com.example.picpaybackend.notification;

import org.springframework.stereotype.Service;

import com.example.picpaybackend.transaction.Transaction;

@Service
public class NotificationProducer {
    public void sendNotification(Transaction transaction) {
        throw new UnsupportedOperationException("Kafka is not used in this application.");
    }
}
