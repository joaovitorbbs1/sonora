package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.ColecaoModel;
import br.edu.ifpe.sonora.model.Visibilidade;
import java.time.LocalDate;

public record ColecaoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Visibilidade visibilidade,
        LocalDate dataCriacao,
        Long usuarioId,
        String usernameProprietario
) {
    public ColecaoResponseDTO(ColecaoModel entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getVisibilidade(),
                entity.getDataCriacao(),
                entity.getUsuario().getId(),
                entity.getUsuario().getUser()
        );
    }
}