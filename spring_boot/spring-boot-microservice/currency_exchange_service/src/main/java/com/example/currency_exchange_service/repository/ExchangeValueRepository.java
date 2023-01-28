package com.example.currency_exchange_service.repository;

import com.example.currency_exchange_service.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue ,UUID> {
    ExchangeValue findByFromCurrencyAndToCurrency(String from, String to);
}
