package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.ArtistaModel;
import br.edu.ifpe.sonora.model.BandaModel;
import br.edu.ifpe.sonora.model.SoloModel;
import br.edu.ifpe.sonora.model.Status;
import java.time.LocalDate;

public record ArtistaResponseDTO(
        Long id,
        String tipo,
        String nomeArtistico,
        String nacionalidade,
        String biografia,
        int anoInicioCarreira,
        String nomeCivil,
        LocalDate dataNasc,
        Status statusAtividade
) {
    public static ArtistaResponseDTO fromEntity(ArtistaModel entity) {
        if (entity instanceof SoloModel solo) {
            return new ArtistaResponseDTO(
                    solo.getId(), "SOLO", solo.getNomeArtistico(), solo.getNacionalidade(),
                    solo.getBiografia(), solo.getAnoInicioCarreira(),
                    solo.getNomeCivil(), solo.getDataNasc(), null
            );
        } else if (entity instanceof BandaModel banda) {
            return new ArtistaResponseDTO(
                    banda.getId(), "BANDA", banda.getNomeArtistico(), banda.getNacionalidade(),
                    banda.getBiografia(), banda.getAnoInicioCarreira(),
                    null, null, banda.getStatusAtividade()
            );
        }
        throw new IllegalArgumentException("Tipo de artista desconhecido");
    }
}