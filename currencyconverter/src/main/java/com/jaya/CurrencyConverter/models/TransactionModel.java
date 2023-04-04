package com.jaya.CurrencyConverter.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@JsonIgnoreProperties(value = {"success", "query", "info"})
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private long userId = 1;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("rate")
    private double rate;
    @JsonProperty("amount")
    private double amount;
    private String from = "EUR";
    @JsonProperty("to")
    private String to;
    @JsonProperty("result")
    private double result;
    @JsonProperty("timestamp")
    private float timestamp;

    public TransactionModel(long transactionId, String to, String from, double amount, double rate) {
        this.transactionId = transactionId;
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.rate = rate;
        this.result = amount*rate;
    }
}

