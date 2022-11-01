package com.jawwad.limitserver.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("data")
public record LimitData(int minimum, int maximum) {
}
