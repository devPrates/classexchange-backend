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
public class Local extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "campus_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
