package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.SeguirModel;
import java.time.LocalDate;

public record SeguirResponseDTO(
        Long seguidorId,
        String usernameSeguidor,
        Long seguidoId,
        String usernameSeguido,
        LocalDate dataInicio
) {
    public SeguirResponseDTO(SeguirModel entity) {
        this(
                entity.getSeguidor().getId(),
                entity.getSeguidor().getUser(),
                entity.getSeguido().getId(),
                entity.getSeguido().getUser(),
                entity.getDataInicio()
        );
    }
}