package com.example.currency_exchange_service.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ExchangeValue {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversion;
    private int port;
}
