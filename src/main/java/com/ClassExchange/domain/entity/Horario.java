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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private com.ClassExchange.domain.enums.DiaSemana diaDaSemana;

    @Column(nullable = false)
    private java.time.LocalTime horaInicio;

    @Column(nullable = false)
    private java.time.LocalTime horaFim;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

}
