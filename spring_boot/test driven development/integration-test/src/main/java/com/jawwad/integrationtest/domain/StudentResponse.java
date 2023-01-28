package com.jawwad.integrationtest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class StudentResponse {
    private UUID id;
    private String name;
    private String email;
    private int age;
}
