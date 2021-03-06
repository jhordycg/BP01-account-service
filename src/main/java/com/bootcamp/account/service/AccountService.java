package com.bootcamp.account.service;

import com.bootcamp.account.model.dao.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> findByIdThenAddBalance(String id, Double mount);

    Mono<Account> findByIdThenReduceBalance(String id, Double mount);

    Mono<Account> findById(String id);

    Flux<Account> findAll();

    Flux<Account> findAllByCustomerIdAndProductId(String customerId, String productId);

    Mono<Account> create(Account account);

    Mono<Void> delete(String id);

}
