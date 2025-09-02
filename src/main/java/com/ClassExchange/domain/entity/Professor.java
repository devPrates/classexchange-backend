package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String siape;

    @Column(nullable = false)
    private String celular;

    @OneToMany(mappedBy = "professor")
    private List<ProfessorClasse> professorClasses;

    @OneToMany(mappedBy = "professor")
    private List<CoordenadorCurso> coordenadorCursos;

    @OneToMany(mappedBy = "professor")
    private List<DiretorEnsino> diretorEnsinos;

}