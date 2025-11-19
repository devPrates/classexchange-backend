package com.ClassExchange.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campus extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 10)
    private String sigla;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    private String telefone;

    private String endereco;

    @OneToMany(mappedBy = "campus")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "campus")
    private List<DiretorEnsino> diretorEnsino;

}
