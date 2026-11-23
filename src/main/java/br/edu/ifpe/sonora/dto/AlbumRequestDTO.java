package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record AlbumRequestDTO(
        @NotBlank(message = "O título do álbum é obrigatório")
        String titulo,

        @NotNull(message = "O gênero musical é obrigatório")
        Genero genero,

        @NotEmpty(message = "Informe pelo menos um artista para o álbum")
        Set<Long> artistasIds
) {}