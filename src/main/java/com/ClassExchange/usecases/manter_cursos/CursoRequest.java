package com.ClassExchange.usecases.manter_cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CursoRequest(

        @NotBlank(message = "O nome do curso é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        String nome,

        @NotBlank(message = "A sigla do curso é obrigatória")
        @Size(max = 10, message = "A sigla deve ter no máximo 10 caracteres")
        String sigla,

        @NotNull(message = "O campus é obrigatório")
        UUID campusId
) {}