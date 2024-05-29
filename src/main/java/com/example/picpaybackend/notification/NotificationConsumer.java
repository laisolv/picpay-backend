package com.example.picpaybackend.notification;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.picpaybackend.transaction.Transaction;

@Service
public class NotificationConsumer {
    private RestTemplate restTemplate;

    public NotificationConsumer() {
        this.restTemplate = new RestTemplate();
    }

    public void receiveNotification(Transaction transaction) {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/73ab33c7-1a5c-42f7-9ec2-8ac954de77d6", Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().message()) {
            throw new NotificationException("Error sending notification!");
        }
    }
}
