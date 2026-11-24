package br.edu.ifpe.sonora.dto;

import jakarta.validation.constraints.NotNull;

public record ColecaoAlbumRequestDTO(
        @NotNull(message = "O ID da coleção é obrigatório")
        Long colecaoId,

        @NotNull(message = "O ID do álbum é obrigatório")
        Long albumId
) {}