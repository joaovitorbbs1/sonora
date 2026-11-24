// 1. Entrada: src/main/java/br/edu/ifpe/sonora/dto/UsuarioRequestDTO.java
package br.edu.ifpe.sonora.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

public record UsuarioRequestDTO(
        @NotBlank(message = "O primeiro nome é obrigatório")
        @Size(max = 20, message = "O primeiro nome deve ter no máximo 20 caracteres")
        String primeiroNome,

        @NotBlank(message = "O sobrenome é obrigatório")
        @Size(max = 60, message = "O sobrenome deve ter no máximo 60 caracteres")
        String sobrenome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O nome de usuário é obrigatório")
        @Size(min = 3, max = 30, message = "O username deve ter entre 3 e 30 caracteres")
        String user,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNasc,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        String estado,

        @NotBlank(message = "O país é obrigatório")
        String pais,

        Set<@NotBlank(message = "Telefone não pode ser em branco") String> telefones
) {}