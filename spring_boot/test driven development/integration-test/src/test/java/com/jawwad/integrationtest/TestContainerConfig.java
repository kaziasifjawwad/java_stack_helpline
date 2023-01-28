package com.jawwad.integrationtest;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainerConfig {

    @Container
    public static PostgreSQLContainer<?> psql = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void configureTestContainersProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", psql::getJdbcUrl);
        registry.add("spring.datasource.username", psql::getUsername);
        registry.add("spring.datasource.password", psql::getPassword);
    }
}
