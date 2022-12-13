package com.jawwad.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication
public class LiquibaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseApplication.class, args);
	}
}
