package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.ColecaoAlbumModel;
import br.edu.ifpe.sonora.model.Genero;
import java.time.LocalDate;

public record ColecaoAlbumResponseDTO(
        Long colecaoId,
        String nomeColecao,
        Long albumId,
        String tituloAlbum,
        Genero generoAlbum,
        LocalDate dataInclusao
) {
    public ColecaoAlbumResponseDTO(ColecaoAlbumModel entity) {
        this(
                entity.getColecao().getId(),
                entity.getColecao().getNome(),
                entity.getAlbum().getId(),
                entity.getAlbum().getTitulo(),
                entity.getAlbum().getGenero(),
                entity.getDataInclusao()
        );
    }
}