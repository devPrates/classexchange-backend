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
public class Curso extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 10)
    private String sigla;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso")
    private List<Turma> turmas;

    @OneToMany(mappedBy = "curso")
    private List<CoordenadorCurso> coordenadorCursos;

    @OneToMany(mappedBy = "curso")
    private List<ProfessorCurso> professorCursos;

    @OneToMany(mappedBy = "curso")
    private List<EstudanteCurso> estudanteCursos;

}