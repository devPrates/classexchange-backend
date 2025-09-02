package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Local extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "local")
    private List<Classe> classes;

}