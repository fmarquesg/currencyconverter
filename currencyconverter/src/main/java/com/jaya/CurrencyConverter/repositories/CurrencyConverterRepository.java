package com.jaya.CurrencyConverter.repositories;

import com.jaya.CurrencyConverter.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyConverterRepository extends JpaRepository<TransactionModel, Long> {

    List<TransactionModel> findByUserId(long userId);
}
