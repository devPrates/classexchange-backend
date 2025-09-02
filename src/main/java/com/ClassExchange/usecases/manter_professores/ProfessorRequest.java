package com.ClassExchange.usecases.manter_professores;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfessorRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ter um formato válido")
        @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
        String email,

        @NotBlank(message = "SIAPE é obrigatório")
        @Size(max = 20, message = "SIAPE deve ter no máximo 20 caracteres")
        String siape,

        @NotBlank(message = "Celular é obrigatório")
        @Size(max = 20, message = "Celular deve ter no máximo 20 caracteres")
        String celular
) {
}