package com.jawwad.liquibase.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Profile_Dev {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String name;
    private int age;
}
