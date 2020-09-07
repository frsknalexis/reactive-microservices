package com.dev.microservices.core.bank.controller;

import com.dev.microservices.core.bank.document.Client;
import com.dev.microservices.core.bank.exception.BankDoesNotExistException;
import com.dev.microservices.core.bank.exception.ClientAlreadyExistsException;
import com.dev.microservices.core.bank.repository.ClientRepository;
import com.dev.microservices.core.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    private final BankService bankService;

    public ClientController(@Autowired @Qualifier("clientRepository") ClientRepository clientRepository,
        @Autowired @Qualifier("bankService") BankService bankService) {
        this.clientRepository = clientRepository;
        this.bankService = bankService;
    }

    @GetMapping(value = "/{id}", produces = { APPLICATION_JSON_VALUE })
    public Mono<Client> findById(@PathVariable(value = "id") String id) {
        return clientRepository.findById(id);
    }

    @GetMapping(value = "/{id}/exists")
    public Mono<Boolean> clientExists(@PathVariable(value = "id") String id) {
        return clientRepository.findById(id)
                .map(client -> TRUE)
                .switchIfEmpty(Mono.just(FALSE));
    }

    @GetMapping(value = "/findByBank/{bankId}", produces = { APPLICATION_JSON_VALUE })
    public Flux<Client> findByBankId(@PathVariable(value = "bankId") String bankId) {
        return clientRepository.findByBankId(bankId);
    }

    @PostMapping(consumes = { APPLICATION_JSON_VALUE },
            produces = { APPLICATION_JSON_VALUE })
    public Mono<Client> registerClient(@RequestBody Client client) {
        return clientRepository.findBySsnAndBankId(client.getSsn(), client.getBankId())
                .zipWith(bankService.bankExists(client.getBankId()))
                .map(tuple -> {
                    if (tuple.getT1() != null) {
                        throw new ClientAlreadyExistsException(client.getSsn(), client.getBankId());
                    }
                    if (!tuple.getT2()) {
                        throw new BankDoesNotExistException(client.getBankId());
                    }
                    return client;
                })
                .map(c -> {
                    c.setRegistrationDate(LocalDate.now());
                    return c;
                })
                .flatMap(clientRepository::save);
    }
}