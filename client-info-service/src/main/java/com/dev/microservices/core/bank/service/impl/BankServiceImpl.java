package com.dev.microservices.core.bank.service.impl;

import com.dev.microservices.core.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service("bankService")
public class BankServiceImpl implements BankService {

    private final WebClient webClient;

    @Value("${services.bank-info-service-port}")
    private String bankInfoServicePort;

    public BankServiceImpl(@Autowired WebClient webClient) {
        this.webClient = webClient;
    }

    private String baseUrl() {
        return "http://localhost:" + bankInfoServicePort + "/banks/";
    }

    @Override
    public Mono<Boolean> bankExists(String bankId) {
        return webClient.get()
                .uri(baseUrl() + "{bankId}/exists", Collections.singletonMap("bankId", bankId))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Boolean.class))
                .single();
    }
}