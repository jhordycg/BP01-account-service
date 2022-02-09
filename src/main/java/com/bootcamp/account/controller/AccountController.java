package com.bootcamp.account.controller;

import com.bootcamp.account.model.dao.Account;
import com.bootcamp.account.model.dao.MovementType;
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
    public Flux<AccountDto> findAll() {
        return service.findAll()
                .mapNotNull(account -> mapper.map(account, AccountDto.class));
    }

    @GetMapping("/")
    public Flux<AccountDto> findAllByCustomerIdAndProductId(
            @RequestParam("customerId") String customerId,
            @RequestParam("productId") String productId
    ) {
        return service.findAllByCustomerIdAndProductId(customerId, productId)
                .mapNotNull(accountFound -> mapper.map(accountFound, AccountDto.class));
    }

    @GetMapping("/{id}")
    public Mono<AccountDto> findById(@PathVariable String id) {
        return service.findById(id)
                .mapNotNull(account -> mapper.map(account, AccountDto.class));
    }

    @PutMapping("/{id}")
    public Mono<AccountDto> findByIdThenAddBalance(
            @PathVariable String id,
            @RequestParam("mount") Double mount,
            @RequestParam("type") MovementType type
    ) {
        Mono<Account> editedAccount;
        switch (type) {
            case INCOME:
                editedAccount = service.findByIdThenAddBalance(id, mount);
                break;
            case OUTCOME:
                editedAccount = service.findByIdThenReduceBalance(id, mount);
                break;
            default:
                return Mono.error(new Exception("Operation not allowed"));
        }
        return editedAccount.mapNotNull(account -> mapper.map(account, AccountDto.class));
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
