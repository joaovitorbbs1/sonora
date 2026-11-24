// 1. UsuarioResponseDTO.java (agora inclui o campo Role role)
package br.edu.ifpe.sonora.dto;

import br.edu.ifpe.sonora.model.Role;
import br.edu.ifpe.sonora.model.UsuarioModel;
import java.time.LocalDate;
import java.util.Set;

public record UsuarioResponseDTO(
        Long id,
        String primeiroNome,
        String sobrenome,
        String email,
        String user,
        Role role,
        LocalDate dataNasc,
        String cidade,
        String estado,
        String pais,
        Set<String> telefones
) {
    public UsuarioResponseDTO(UsuarioModel entity) {
        this(
                entity.getId(),
                entity.getPrimeiroNome(),
                entity.getSobrenome(),
                entity.getEmail(),
                entity.getUser(),
                entity.getRole(),
                entity.getDataNasc(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getPais(),
                entity.getTelefones()
        );
    }
}