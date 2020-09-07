package com.dev.microservices.core.bank.controller;

import com.dev.microservices.core.bank.document.Bank;
import com.dev.microservices.core.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/banks")
public class BankController {

    private final BankRepository bankRepository;

    public BankController(@Autowired @Qualifier("bankRepository") BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping(produces = { APPLICATION_JSON_VALUE })
    public Flux<Bank> allBanks() {
        return bankRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = { APPLICATION_JSON_VALUE })
    public Mono<Bank> findById(@PathVariable(value = "id") String id) {
        return bankRepository.findById(id);
    }

    @GetMapping(value = "/{id}/exists")
    public Mono<Boolean> bankExists(@PathVariable(value = "id") String id) {
        return bankRepository.findById(id)
                .map(bank -> Boolean.TRUE)
                .switchIfEmpty(Mono.just(Boolean.FALSE));
    }

    @GetMapping(value = "/findByName", produces = { APPLICATION_JSON_VALUE })
    public Flux<Bank> findByName(@RequestParam(value = "name") String name) {
        return bankRepository.findByName(name);
    }

    @PostMapping(consumes = { APPLICATION_JSON_VALUE },
        produces = { APPLICATION_JSON_VALUE })
    public Mono<Bank> createBank(@RequestBody Mono<Bank> bank) {
        return bank.flatMap(b -> {
            b.setFoundationDate(LocalDate.now());
            return bankRepository.save(b);
        });
    }
}