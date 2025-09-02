package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorClasse extends BaseEntity {

    @Column(nullable = false)
    private LocalDate inicio;

    @Column
    private LocalDate fim;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

}