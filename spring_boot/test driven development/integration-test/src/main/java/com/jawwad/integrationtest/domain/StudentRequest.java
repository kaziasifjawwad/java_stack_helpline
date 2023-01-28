package com.jawwad.integrationtest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;
@Accessors(chain = true)
@Data
public class StudentRequest {
    private String name;
    private String email;
    private int age;
}
