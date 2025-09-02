package com.ClassExchange.domain.entity;

import com.ClassExchange.domain.enums.SituacaoClasse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudanteClasse extends BaseEntity {

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private LocalDate vinculoCurso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoClasse situacao;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

}