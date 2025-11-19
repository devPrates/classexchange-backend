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

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @OneToMany(mappedBy = "campus")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "campus")
    private List<DiretorEnsino> diretorEnsino;

}
