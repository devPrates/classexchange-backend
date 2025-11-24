package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"estudante_curso_id", "aula_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudanteDisciplina extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "estudante_curso_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EstudanteCurso estudanteCurso;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;
}
