package com.bootcamp.account.controller;

import com.bootcamp.account.model.dao.Account;
import com.bootcamp.account.model.dto.AccountDto;
import com.bootcamp.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {
    private final AccountService service;
    private final ModelMapper mapper;

    @GetMapping
    public Flux<AccountDto> findAllByCustomerIdAndProductId(
            @RequestParam("customerId") String customerId,
            @RequestParam("productId") String productId
    ) {
        return service.findAllByCustomerIdAndProductId(customerId, productId)
                .mapNotNull(accountFound -> mapper.map(accountFound, AccountDto.class));
    }


    @PutMapping("/{id}")
    public Mono<AccountDto> findByIdThenAddBalance(
            @PathVariable String id,
            @RequestParam("mount") Double mount
    ) {
        return service.findByIdThenAddBalance(id, mount)
                .mapNotNull(account -> mapper.map(account, AccountDto.class));
    }

    @PostMapping
    public Mono<AccountDto> create(@RequestBody Account account) {
        return service.create(account).mapNotNull(createdAccount -> mapper.map(createdAccount, AccountDto.class));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
