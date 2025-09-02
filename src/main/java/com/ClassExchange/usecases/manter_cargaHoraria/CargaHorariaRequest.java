package com.ClassExchange.usecases.manter_cargaHoraria;

import com.ClassExchange.domain.enums.MedidaTempo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CargaHorariaRequest(

        @NotBlank(message = "O nome da carga horária é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A duração é obrigatória")
        @Positive(message = "A duração deve ser um valor positivo")
        Integer duracao,

        @NotNull(message = "A medida de tempo é obrigatória")
        MedidaTempo medidaTempo
) {}