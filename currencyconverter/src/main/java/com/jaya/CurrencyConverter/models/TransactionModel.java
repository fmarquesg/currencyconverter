package com.jaya.CurrencyConverter.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;


@Entity
@Table(name = "transaction")
@NoArgsConstructor
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private long userId = 1;
    @JsonProperty("date")
    private Instant date = Instant.now();
    @JsonProperty("rate")
    private double rate;
    @JsonProperty("amount")
    private double amount;
    private String from = "EUR";
    @JsonProperty("to")
    private String to;
    @JsonProperty("result")
    private double result;

    @Autowired
    public TransactionModel(long transactionId, double rate, double amount, String to, double result) {
        this.transactionId = transactionId;
        this.rate = rate;
        this.amount = amount;
        this.to = to;
        this.result = result;
    }
}

