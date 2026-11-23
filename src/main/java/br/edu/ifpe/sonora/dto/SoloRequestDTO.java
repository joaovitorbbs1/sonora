package br.edu.ifpe.sonora.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record SoloRequestDTO(
        @NotBlank(message = "O nome artístico é obrigatório")
        @Size(max = 50, message = "O nome artístico deve ter no máximo 50 caracteres")
        String nomeArtistico,

        @NotBlank(message = "A nacionalidade é obrigatória")
        @Size(max = 30, message = "A nacionalidade deve ter no máximo 30 caracteres")
        String nacionalidade,

        String biografia,

        @NotNull(message = "O ano de início de carreira é obrigatório")
        @Positive(message = "O ano de início deve ser válido")
        Integer anoInicioCarreira,

        @NotBlank(message = "O nome civil é obrigatório")
        String nomeCivil,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNasc
) {}