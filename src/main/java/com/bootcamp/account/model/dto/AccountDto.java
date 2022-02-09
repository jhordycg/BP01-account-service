package com.bootcamp.account.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class AccountDto {
    @Id
    private String id;
    private String customerId;
    private String productId;
    private boolean balance;
    private Date enrolled;
    private Integer movements;
}
