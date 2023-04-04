package com.jaya.CurrencyConverter.controllers;

import com.jaya.CurrencyConverter.models.TransactionModel;
import com.jaya.CurrencyConverter.services.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/currencyconverter")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping(value = "/conversionresult/{coin}/{amount}")
    public void getConversionResult(@PathVariable String coin, @PathVariable String amount) throws IOException {
        currencyConverterService.convertCoinValueAToCoinValueB(coin, amount);
    }

    @GetMapping(value = "/transactionbyid/{id}")
    public Optional<TransactionModel> getTransactionById(@PathVariable Long id) {
        return currencyConverterService.getTransactionById(id);
    }

    @GetMapping(value = "/transactionbyuser/{userId}")
    public Optional<Object> getAllTransactionsByUserId(@PathVariable Long userId) {
        return currencyConverterService.getTransactionsByUser(userId);
    }

}


