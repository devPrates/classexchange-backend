package com.ClassExchange.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campus extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 10)
    private String sigla;

    @Column(nullable = false, unique = true)
    private String email;

}
