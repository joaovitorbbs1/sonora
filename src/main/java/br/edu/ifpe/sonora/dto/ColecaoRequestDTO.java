package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.Visibilidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ColecaoRequestDTO(
        @NotBlank(message = "O nome da coleção é obrigatório")
        @Size(max = 40, message = "O nome da coleção deve ter no máximo 40 caracteres")
        String nome,

        String descricao,

        @NotNull(message = "A visibilidade da coleção é obrigatória")
        Visibilidade visibilidade,

        @NotNull(message = "O ID do usuário proprietário é obrigatório")
        Long usuarioId
) {}