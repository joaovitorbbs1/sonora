package br.edu.ifpe.sonora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FaixaRequestDTO(
        @NotNull(message = "O ID do álbum da edição é obrigatório")
        Long albumId,

        @NotNull(message = "O número da edição é obrigatório")
        @Positive(message = "O número da edição deve ser maior que zero")
        Long numeroEdicao,

        @NotBlank(message = "O título da faixa é obrigatório")
        String titulo,

        @NotNull(message = "A ordem da faixa é obrigatória")
        @Positive(message = "A ordem da faixa deve ser maior que zero")
        Integer ordem,

        @NotNull(message = "A duração em segundos é obrigatória")
        @Positive(message = "A duração deve ser maior que zero")
        Integer duracao
) {}