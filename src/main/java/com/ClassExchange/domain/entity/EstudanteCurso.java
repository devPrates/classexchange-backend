package com.ClassExchange.domain.entity;

import com.ClassExchange.domain.enums.SituacaoClasse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"estudante_id", "curso_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudanteCurso extends BaseEntity {

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private LocalDate vinculoCurso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoClasse situacao;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Curso curso;
}
