package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.EdicaoModel;
import br.edu.ifpe.sonora.model.Formatos;
import java.time.LocalDate;

public record EdicaoResponseDTO(
        Long albumId,
        String tituloAlbum,
        Long numeroEdicao,
        Formatos formato,
        String gravadora,
        LocalDate dataLancamento
) {
    public EdicaoResponseDTO(EdicaoModel entity) {
        this(
                entity.getEdicaoID().getAlbumId(),
                entity.getAlbum().getTitulo(),
                entity.getEdicaoID().getNumeroEdicao(),
                entity.getFormato(),
                entity.getGravadora(),
                entity.getDataLancamento()
        );
    }
}