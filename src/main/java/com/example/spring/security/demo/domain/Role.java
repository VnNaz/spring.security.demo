package com.example.spring.security.demo.domain;

import com.example.spring.security.demo.enums.Authority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private Authority authority;
    public Role(Authority authority) {
        this.authority = authority;
    }
}
