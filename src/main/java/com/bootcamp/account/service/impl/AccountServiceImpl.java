package com.bootcamp.account.service.impl;

import com.bootcamp.account.model.dao.Account;
import com.bootcamp.account.repository.AccountRepository;
import com.bootcamp.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public Flux<Account> findAllByCustomerIdAndProductId(String customerId, String productId) {
        return repository.findAllByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public Mono<Account> create(Account account) {
        return repository.findOne(Example.of(account)).hasElement()
                .flatMap(existAccount -> {
                    if (!existAccount) return repository.save(account);
                    else return Mono.empty();
                }).single();
    }
}
