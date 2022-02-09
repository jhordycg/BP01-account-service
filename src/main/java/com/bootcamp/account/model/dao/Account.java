package com.bootcamp.account.model.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDate;

@Data
@Document
public class Account {
    @Id
    private String id;
    private String customerId;
    private String productId;
    private Double balance;
    private LocalDate enrolled = LocalDate.now();
    private Integer movements;
}
