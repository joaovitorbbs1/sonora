package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.AlbumModel;
import br.edu.ifpe.sonora.model.Genero;
import java.util.List;

public record AlbumResponseDTO(
        Long id,
        String titulo,
        Genero genero,
        List<ArtistaResumoDTO> artistas
) {
    public AlbumResponseDTO(AlbumModel entity) {
        this(
                entity.getId(),
                entity.getTitulo(),
                entity.getGenero(),
                entity.getArtistas().stream()
                        .map(ArtistaResumoDTO::new)
                        .toList()
        );
    }
}