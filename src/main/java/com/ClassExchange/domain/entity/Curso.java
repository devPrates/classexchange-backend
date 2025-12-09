package com.ClassExchange.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Campus campus;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Turma> turmas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CoordenadorCurso> coordenadorCursos;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProfessorCurso> professorCursos;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EstudanteCurso> estudanteCursos;

}
