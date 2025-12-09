package com.ClassExchange.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campus extends BaseEntity {

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Sigla é obrigatória")
    @Column(nullable = false, length = 10)
    private String sigla;

    @NotBlank(message = "Email é obrigatório")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Slug é obrigatório")
    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    private String telefone;

    private String endereco;

    @OneToMany(mappedBy = "campus", cascade = jakarta.persistence.CascadeType.REMOVE, orphanRemoval = true)
    private List<Curso> cursos;

    @OneToMany(mappedBy = "campus", cascade = jakarta.persistence.CascadeType.REMOVE, orphanRemoval = true)
    private List<DiretorEnsino> diretorEnsino;

}
