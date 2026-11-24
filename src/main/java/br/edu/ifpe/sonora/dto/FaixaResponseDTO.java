package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.FaixaModel;

public record FaixaResponseDTO(
        Long id,
        String titulo,
        int ordem,
        int duracaoSegundos,
        String duracaoFormatada, // Campo derivado amigável (ex: "03:45") para o frontend
        Long albumId,
        Long numeroEdicao
) {
    public FaixaResponseDTO(FaixaModel entity) {
        this(
                entity.getId(),
                entity.getTitulo(),
                entity.getOrdem(),
                entity.getDuracao(),
                formatarDuracao(entity.getDuracao()),
                entity.getEdicao().getEdicaoID().getAlbumId(),
                entity.getEdicao().getEdicaoID().getNumeroEdicao()
        );
    }

    private static String formatarDuracao(int totalSegundos) {
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}