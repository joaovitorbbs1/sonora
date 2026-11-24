package br.edu.ifpe.sonora.dto;

import jakarta.validation.constraints.NotNull;

public record SeguirRequestDTO(
        @NotNull(message = "O ID do seguidor é obrigatório")
        Long seguidorId,

        @NotNull(message = "O ID do usuário seguido é obrigatório")
        Long seguidoId
) {}