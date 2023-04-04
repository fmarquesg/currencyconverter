package com.jaya.CurrencyConverter.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaya.CurrencyConverter.models.TransactionModel;
import com.jaya.CurrencyConverter.repositories.CurrencyConverterRepository;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyConverterService {

    @Autowired
    CurrencyConverterRepository currencyConverterRepository;

    public void convertCoinValueAToCoinValueB(String coin, String amount) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String regexError4XX = "4\\d{4}";
        String regexError5XX = "5\\d{5}";

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=EUR&from=" + coin + "&amount=" + amount)
                .addHeader("apikey", "jm5Gxd1eZ9a1Vlfv2UsIB84GDgj4CtNS")
                .build();
        Response response = client.newCall(request).execute();

        try {
            if (response.code() != Integer.parseInt(regexError4XX) && response.code() != Integer.parseInt(regexError5XX)) {
                TransactionModel filledTransactionModel;
                ObjectMapper objectMapper = new ObjectMapper();

                filledTransactionModel = objectMapper.readValue(response.body().string(), TransactionModel.class);
                currencyConverterRepository.save(filledTransactionModel);
                System.out.println("Data from latest transaction = \n" + response.body().toString());
            } else {
                log.error("It was not possible to finish the required operation.");
                log.error("The error code is " + response.code());
            }

        } catch (JsonProcessingException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Optional<TransactionModel> getTransactionById(long transactionId) {
        boolean exists = currencyConverterRepository.existsById(transactionId);
        if (!exists) {
            throw new IllegalStateException("The transaction with id " + transactionId + " does not exist");
        } else {
            log.info("############## Retrieving a transaction by ID ###############");
            System.out.println("Data from latest transaction = \n" + currencyConverterRepository.findById(transactionId));
            return currencyConverterRepository.findById(transactionId);
        }
    }

    public Optional<Object> getTransactionsByUser(long userId) {
        try {
            currencyConverterRepository.findByUserId(userId).forEach(id -> {
                System.out.println();
            });
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
            log.error("The list of transactions is empty.");
        }
        return Optional.empty();
    }
}


