package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classe extends BaseEntity {

    @Column(nullable = false)
    private int vagas;

    @Column(nullable = false)
    private int numeroAulas;

    @Column(nullable = false)
    private LocalDate inicio;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "carga_horaria_id", nullable = false)
    private CargaHoraria cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    @OneToMany(mappedBy = "classe")
    private List<Horario> horarios;

    @OneToMany(mappedBy = "classe")
    private List<EstudanteClasse> estudanteClasses;

    @OneToMany(mappedBy = "classe")
    private List<ProfessorClasse> professorClasses;

}