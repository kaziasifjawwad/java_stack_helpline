package com.example.currency_exchange_service.controller;

import com.example.currency_exchange_service.entity.ExchangeValue;
import com.example.currency_exchange_service.service.ExchangeValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency-exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;
    private final ExchangeValueService exchangeValueService;

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<List<ExchangeValue>> retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        return ResponseEntity.ok(
                this.exchangeValueService.getAllExchangeValue()
        );
    }

}
