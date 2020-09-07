package com.dev.microservices.core.bank.repository;

import com.dev.microservices.core.bank.document.Bank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository("bankRepository")
public interface BankRepository extends ReactiveMongoRepository<Bank, String> {

    Flux<Bank> findByName(String name);
}