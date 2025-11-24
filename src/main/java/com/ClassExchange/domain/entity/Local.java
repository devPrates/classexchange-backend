package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Local extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    @Column
    private Integer capacidade;

    @Column
    private String bloco;

    @Column
    private String andar;

    @Column
    private String tipo;

}
