package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.ArtistaModel;

public record ArtistaResumoDTO(Long id, String nomeArtistico) {
    public ArtistaResumoDTO(ArtistaModel entity) {
        this(entity.getId(), entity.getNomeArtistico());
    }
}