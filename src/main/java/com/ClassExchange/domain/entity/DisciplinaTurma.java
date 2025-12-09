package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"disciplina_id", "turma_id"})
})
public class DisciplinaTurma extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    @OneToMany(mappedBy = "disciplinaTurma")
    private java.util.List<ProfessorDisciplina> professorDisciplinas;
}
