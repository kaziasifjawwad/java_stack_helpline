package com.example.currency_exchange_service.runner;

import com.example.currency_exchange_service.entity.ExchangeValue;
import com.example.currency_exchange_service.repository.ExchangeValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ExchangeValueDataGenerator implements CommandLineRunner {
    private final ExchangeValueRepository exchangeValueRepository;
    private final Environment environment;

    @Override
    public void run(String... args) throws Exception {
        this.exchangeValueRepository.save(
                new ExchangeValue().setFrom_currency("USD")
                        .setTo_currency("BDT")
                        .setConversionMultiple(BigDecimal.valueOf(100))
                        .setServerPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty(("local.server.port")))))
        );
    }
}
