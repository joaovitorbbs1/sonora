package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.dto.SeguirRequestDTO;
import br.edu.ifpe.sonora.dto.SeguirResponseDTO;
import br.edu.ifpe.sonora.model.SeguirID;
import br.edu.ifpe.sonora.model.SeguirModel;
import br.edu.ifpe.sonora.model.UsuarioModel;
import br.edu.ifpe.sonora.repository.SeguirRepository;
import br.edu.ifpe.sonora.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeguirService {

    private final SeguirRepository seguirRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguirService(SeguirRepository seguirRepository, UsuarioRepository usuarioRepository) {
        this.seguirRepository = seguirRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public SeguirResponseDTO seguir(SeguirRequestDTO dto) {
        if (dto.seguidorId().equals(dto.seguidoId())) {
            throw new IllegalArgumentException("Um usuário não pode seguir a si mesmo.");
        }

        SeguirID seguirID = new SeguirID(dto.seguidorId(), dto.seguidoId());
        if (seguirRepository.existsById(seguirID)) {
            throw new IllegalArgumentException("Você já segue este usuário.");
        }

        UsuarioModel seguidor = usuarioRepository.findById(dto.seguidorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário seguidor não encontrado."));

        UsuarioModel seguido = usuarioRepository.findById(dto.seguidoId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário a ser seguido não encontrado."));

        SeguirModel relacionamento = new SeguirModel(
                seguirID,
                seguidor,
                seguido,
                LocalDate.now()
        );

        return new SeguirResponseDTO(seguirRepository.save(relacionamento));
    }

    @Transactional
    public void deixarDeSeguir(Long seguidorId, Long seguidoId) {
        SeguirID seguirID = new SeguirID(seguidorId, seguidoId);
        if (!seguirRepository.existsById(seguirID)) {
            throw new IllegalArgumentException("Relacionamento de seguimento não encontrado.");
        }
        seguirRepository.deleteById(seguirID);
    }

    @Transactional(readOnly = true)
    public List<SeguirResponseDTO> listarSeguidores(Long usuarioId) {
        return seguirRepository.findById_SeguidoIDOrderByDataInicioDesc(usuarioId).stream()
                .map(SeguirResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SeguirResponseDTO> listarSeguindo(Long usuarioId) {
        return seguirRepository.findById_SeguidorIDOrderByDataInicioDesc(usuarioId).stream()
                .map(SeguirResponseDTO::new)
                .toList();
    }
}