package com.ClassExchange.domain.entity;

import com.ClassExchange.domain.enums.RoleUsuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String celular;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUsuario role;
}