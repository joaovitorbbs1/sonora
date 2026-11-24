package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.Formatos;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record EdicaoRequestDTO(
        @NotNull(message = "O ID do álbum é obrigatório")
        Long albumId,

        @NotNull(message = "O número da edição é obrigatório")
        @Positive(message = "O número da edição deve ser positivo")
        Long numeroEdicao,

        @NotNull(message = "O formato é obrigatório")
        Formatos formato,

        @NotBlank(message = "A gravadora é obrigatória")
        @Size(max = 60, message = "A gravadora deve ter no máximo 60 caracteres")
        String gravadora,

        @NotNull(message = "A data de lançamento é obrigatória")
        @PastOrPresent(message = "A data de lançamento não pode ser futura")
        LocalDate dataLancamento
) {}