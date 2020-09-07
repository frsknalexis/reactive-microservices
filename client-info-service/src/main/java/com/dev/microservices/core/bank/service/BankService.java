package com.dev.microservices.core.bank.service;

import reactor.core.publisher.Mono;

public interface BankService {

    Mono<Boolean> bankExists(String bankId);
}