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
    public Mono<Account> findByIdThenAddBalance(String id, Double mount) {
        return repository.findById(id).mapNotNull(account -> {
            var currentBalance = account.getBalance();
            account.setBalance(currentBalance + mount);
            return repository.save(account);
        }).flatMap(accountMono -> accountMono);
    }

    @Override
    public Mono<Account> findByIdThenReduceBalance(String id, Double mount) {
        return repository.findById(id).flatMap(account -> {
            var currentBalance = account.getBalance();
            var updatedBalance = currentBalance - mount;
            if (updatedBalance < 0) return Mono.error(new Exception("Insufficient balance"));

            account.setBalance(updatedBalance);
            return repository.save(account);
        });
    }

    @Override
    public Mono<Account> findById(String id) {
        return repository.findById(id).single();
    }

    @Override
    public Flux<Account> findAll() {
        return repository.findAll();
    }

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

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }


}
