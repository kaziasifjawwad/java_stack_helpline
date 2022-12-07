package com.example.currency_exchange_service.controller;

import com.example.currency_exchange_service.pojo.ExchangeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/currency-exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<ExchangeValue> retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        return ResponseEntity.ok(new ExchangeValue()
                .setFrom("USD")
                .setTo("BDT")
                .setConvertion((BigDecimal.valueOf(45L)))
                .setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty(("local.server.port")
                                ))
                        )
                )
        );
    }

}
