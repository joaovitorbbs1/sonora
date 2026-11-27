package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.model.Role;
import br.edu.ifpe.sonora.model.UsuarioModel;
import br.edu.ifpe.sonora.dto.LoginRequestDTO;
import br.edu.ifpe.sonora.dto.UsuarioRequestDTO;
import br.edu.ifpe.sonora.dto.UsuarioResponseDTO;
import br.edu.ifpe.sonora.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência via construtor (melhor prática no Spring)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Este e-mail já está cadastrado no SONORA.");
        }
        if (usuarioRepository.existsByUser(dto.user())) {
            throw new IllegalArgumentException("Este nome de usuário já está em uso.");
        }

        // Gera hash MD5/SHA simples nativo do Spring para não salvar senha em texto puro
        String senhaHash = DigestUtils.md5DigestAsHex(dto.senha().getBytes(StandardCharsets.UTF_8));

        UsuarioModel novoUsuario = new UsuarioModel(
                dto.primeiroNome(),
                dto.sobrenome(),
                dto.email(),
                dto.user(),
                senhaHash,
                dto.cidade(),
                dto.dataNasc(),
                dto.estado(),
                dto.pais(),
                dto.telefones()
        );
        // Todo novo cadastro nasce obrigatoriamente como USUARIO comum
        novoUsuario.setRole(Role.USUARIO);

        UsuarioModel salvo = usuarioRepository.save(novoUsuario);
        return new UsuarioResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO autenticar(LoginRequestDTO dto) {
        UsuarioModel usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos."));

        String senhaDigitadaHash = DigestUtils.md5DigestAsHex(dto.senha().getBytes(StandardCharsets.UTF_8));

        if (!usuario.getSenha().equals(senhaDigitadaHash)) {
            throw new IllegalArgumentException("E-mail ou senha inválidos.");
        }

        // Retorna o DTO (com id e role, mas sem a senha) para o frontend manter a sessão
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }
}