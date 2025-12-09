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
public class Aula extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario professor;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Local local;
}

