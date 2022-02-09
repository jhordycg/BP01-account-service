package com.bootcamp.account.model.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.Date;

@Data
@Document
public class Account {
    @Id
    private String id;
    private String customerId;
    private String productId;
    private boolean balance;
    private Date enrolled = new Date();
    private Integer movements;
}
