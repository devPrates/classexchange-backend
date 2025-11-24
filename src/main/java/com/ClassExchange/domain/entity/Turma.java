package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"slug", "curso_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turma extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 100)
    private String slug;

    @Column(nullable = false)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "turma")
    private List<Periodo> periodos;

}
