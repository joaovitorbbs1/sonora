package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.Status;
import jakarta.validation.constraints.*;

public record BandaRequestDTO(
        @NotBlank(message = "O nome da banda é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        String nomeArtistico,

        @NotBlank(message = "A nacionalidade é obrigatória")
        @Size(max = 30, message = "A nacionalidade deve ter no máximo 30 caracteres")
        String nacionalidade,

        String biografia,

        @NotNull(message = "O ano de formação é obrigatório")
        @Positive(message = "O ano de formação deve ser válido")
        Integer anoInicioCarreira,

        @NotNull(message = "O status de atividade é obrigatório")
        Status statusAtividade
) {}