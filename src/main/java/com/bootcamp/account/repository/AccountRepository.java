package com.bootcamp.account.repository;

import com.bootcamp.account.model.dao.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findAllByCustomerIdAndProductId(String customerId, String productId);
}
