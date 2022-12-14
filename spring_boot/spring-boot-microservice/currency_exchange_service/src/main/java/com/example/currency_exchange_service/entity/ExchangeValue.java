package com.example.currency_exchange_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExchangeValue {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(name = "from_currency")
    private String fromCurrency;
    @Column(name = "to_currencty")
    private String toCurrency;
    private BigDecimal conversionMultiple;
    private int serverPort;
}
