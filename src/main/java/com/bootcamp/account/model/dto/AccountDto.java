package com.bootcamp.account.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class AccountDto {
    @Id
    private String id;
    private String customerId;
    private String productId;
    private Double balance;
    private LocalDate enrolled;
    private Integer movements;
}
