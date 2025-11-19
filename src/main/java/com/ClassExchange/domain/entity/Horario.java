package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horario extends BaseEntity {

    @Column(nullable = false)
    private int diaDaSemana;

    @Column(nullable = false)
    private String horaInicio;

    @Column(nullable = false)
    private String horaFim;

    @ManyToOne
    @JoinColumn(name = "disciplina_turma_id", nullable = false)
    private DisciplinaTurma disciplinaTurma;

    @ManyToOne
    @JoinColumn(name = "carga_horaria_id", nullable = false)
    private CargaHoraria cargaHoraria;

}