package com.jawwad.ss_l3.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name="authorities")
@Data
@Accessors(chain = true)
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;
}
