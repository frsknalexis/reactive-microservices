package com.dev.microservices.core.bank.repository;

import com.dev.microservices.core.bank.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("clientRepository")
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

    Flux<Client> findByBankId(String bankId);

    Mono<Client> findBySsnAndBankId(String ssn, String bankId);
}