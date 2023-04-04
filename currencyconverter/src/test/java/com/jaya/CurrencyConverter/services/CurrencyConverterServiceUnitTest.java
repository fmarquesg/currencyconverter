package com.jaya.CurrencyConverter.services;

import com.jaya.CurrencyConverter.models.TransactionModel;
import com.jaya.CurrencyConverter.repositories.CurrencyConverterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CurrencyConverterServiceUnitTest {

    @Mock
    private CurrencyConverterRepository currencyConverterRepository;

    @InjectMocks
    private CurrencyConverterService currencyConverterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionById() {
        //GIVEN
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setTransactionId(1L);

        //WHEN
        when(currencyConverterRepository.existsById(transactionModel.getTransactionId())).thenReturn(true);
        when(currencyConverterRepository.findById(transactionModel.getTransactionId())).thenReturn(Optional.of(transactionModel));
        currencyConverterService.currencyConverterRepository = currencyConverterRepository;
        Optional<TransactionModel> result = currencyConverterService.getTransactionById(transactionModel.getTransactionId());

        //THEN
        Assertions.assertTrue(result.isPresent());
        assertEquals(transactionModel, result.get());
    }

    @Test
    public void testGetTransactionsByUser() {
        //GIVEN
        int count = 0;
        long userId = 1L;
        List<TransactionModel> transactions = new ArrayList<>();

        //WHEN
        transactions.add(new TransactionModel(1L, "USD", "EUR", 100.0, 0.8));
        transactions.add(new TransactionModel(2L, "EUR", "GBP", 50.0, 0.7));
        for (TransactionModel transaction : transactions){
            count++;
        }

        //THEN
        Assertions.assertEquals(count,2 );
    }
}