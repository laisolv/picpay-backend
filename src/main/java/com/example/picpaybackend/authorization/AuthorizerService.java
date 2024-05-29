package com.example.picpaybackend.authorization;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.picpaybackend.transaction.Transaction;

@Service
public class AuthorizerService {
    private RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://run.mocky.io/v3/73ab33c7-1a5c-42f7-9ec2-8ac954de77d6")
                .build();
    }

    public void authorize(Transaction transaction) {
        var response = restClient.get()
                .retrieve()
                .toEntity(Authorization.class);

        if (response.getStatusCode().isError() || response.getBody().isAuthorized()) {
            throw new UnauthorizedTransactioException("Unauthorized transaction!");
        }
    }
}
