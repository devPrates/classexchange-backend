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
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

}