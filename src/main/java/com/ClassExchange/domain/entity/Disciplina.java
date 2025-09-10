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
public class Disciplina extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double cargaHoraria;

    @Column(columnDefinition = "TEXT")
    private String ementa;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "disciplina")
    private List<Periodo> periodos;

    @OneToMany(mappedBy = "disciplina")
    private List<Classe> classes;

}