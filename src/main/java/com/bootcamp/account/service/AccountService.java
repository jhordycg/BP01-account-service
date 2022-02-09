package com.bootcamp.account.service;

import com.bootcamp.account.model.dao.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Flux<Account> findAllByCustomerIdAndProductId(String customerId, String productId);
    Mono<Account> create(Account account);
}
