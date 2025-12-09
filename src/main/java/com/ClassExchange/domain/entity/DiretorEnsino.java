package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiretorEnsino extends BaseEntity {

    @Column(nullable = false)
    private LocalDate inicio;

    @Column
    private LocalDate fim;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "campus_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Campus campus;

}
