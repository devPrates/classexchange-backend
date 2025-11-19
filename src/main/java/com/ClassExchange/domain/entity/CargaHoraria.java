package com.ClassExchange.domain.entity;

import com.ClassExchange.domain.enums.MedidaTempo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargaHoraria extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int duracao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedidaTempo medidaTempo;

    @OneToMany(mappedBy = "cargaHoraria")
    private List<Horario> horarios;

}