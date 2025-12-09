package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"slug", "curso_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disciplina extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 100)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Periodo periodo;

    @Column(nullable = true)
    private Double cargaHoraria;

    @Column(columnDefinition = "TEXT")
    private String ementa;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Curso curso;


}
