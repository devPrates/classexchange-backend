package com.ClassExchange.usecases.manter_usuarios;

import com.ClassExchange.domain.enums.RoleUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UsuarioRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 120, message = "Nome deve ter entre 2 e 120 caracteres")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String senha,

        @NotBlank(message = "Celular é obrigatório")
        @Pattern(regexp = "^[0-9()+\\-\\s]{8,20}$", message = "Celular inválido")
        String celular,

        @NotNull(message = "Role é obrigatória")
        RoleUsuario role,

        @NotNull(message = "Campus é obrigatório")
        UUID campusId
) {}