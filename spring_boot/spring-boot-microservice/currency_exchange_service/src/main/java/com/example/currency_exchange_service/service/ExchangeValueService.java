package com.example.currency_exchange_service.service;

import com.example.currency_exchange_service.entity.ExchangeValue;
import com.example.currency_exchange_service.repository.ExchangeValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExchangeValueService {
    private final ExchangeValueRepository exchangeValueRepository;

    public List<ExchangeValue> getAllExchangeValue() {
        return this.exchangeValueRepository.findAll();
    }

    public ExchangeValue findByFromAndTo(String from, String to) {
        return this.exchangeValueRepository.findByFromCurrencyAndToCurrency(from, to);
    }
}
