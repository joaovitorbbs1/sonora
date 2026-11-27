package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.dto.ColecaoAlbumRequestDTO;
import br.edu.ifpe.sonora.dto.ColecaoAlbumResponseDTO;
import br.edu.ifpe.sonora.dto.ColecaoRequestDTO;
import br.edu.ifpe.sonora.dto.ColecaoResponseDTO;
import br.edu.ifpe.sonora.model.*;
import br.edu.ifpe.sonora.repository.AlbumRepository;
import br.edu.ifpe.sonora.repository.ColecaoAlbumRepository;
import br.edu.ifpe.sonora.repository.ColecaoRepository;
import br.edu.ifpe.sonora.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ColecaoService {

    private final ColecaoRepository colecaoRepository;
    private final ColecaoAlbumRepository colecaoAlbumRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlbumRepository albumRepository;

    public ColecaoService(ColecaoRepository colecaoRepository,
                          ColecaoAlbumRepository colecaoAlbumRepository,
                          UsuarioRepository usuarioRepository,
                          AlbumRepository albumRepository) {
        this.colecaoRepository = colecaoRepository;
        this.colecaoAlbumRepository = colecaoAlbumRepository;
        this.usuarioRepository = usuarioRepository;
        this.albumRepository = albumRepository;
    }

    @Transactional
    public ColecaoResponseDTO criarColecao(ColecaoRequestDTO dto) {
        UsuarioModel proprietario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        ColecaoModel colecao = new ColecaoModel(
                dto.nome(),
                dto.descricao(),
                dto.visibilidade(),
                LocalDate.now(),
                proprietario
        );

        return new ColecaoResponseDTO(colecaoRepository.save(colecao));
    }

    @Transactional
    public ColecaoAlbumResponseDTO adicionarAlbum(ColecaoAlbumRequestDTO dto) {
        ColecaoAlbumID idComposto = new ColecaoAlbumID(dto.albumId(), dto.colecaoId());
        if (colecaoAlbumRepository.existsById(idComposto)) {
            throw new IllegalArgumentException("Este álbum já está cadastrado nesta coleção.");
        }

        ColecaoModel colecao = colecaoRepository.findById(dto.colecaoId())
                .orElseThrow(() -> new IllegalArgumentException("Coleção não encontrada."));

        AlbumModel album = albumRepository.findById(dto.albumId())
                .orElseThrow(() -> new IllegalArgumentException("Álbum não encontrado."));

        ColecaoAlbumModel vinculo = new ColecaoAlbumModel(
                idComposto,
                colecao,
                album,
                LocalDate.now()
        );

        return new ColecaoAlbumResponseDTO(colecaoAlbumRepository.save(vinculo));
    }

    @Transactional
    public void removerAlbum(Long colecaoId, Long albumId) {
        ColecaoAlbumID idComposto = new ColecaoAlbumID(albumId, colecaoId);
        if (!colecaoAlbumRepository.existsById(idComposto)) {
            throw new IllegalArgumentException("O álbum informado não faz parte desta coleção.");
        }
        colecaoAlbumRepository.deleteById(idComposto);
    }

    @Transactional(readOnly = true)
    public List<ColecaoResponseDTO> listarColecoesDoProprioUsuario(Long usuarioId) {
        return colecaoRepository.findByUsuario_IdOrderByDataCriacaoDesc(usuarioId).stream()
                .map(ColecaoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColecaoResponseDTO> listarColecoesPublicasDoUsuario(Long usuarioId) {
        return colecaoRepository.findByUsuario_IdAndVisibilidade(usuarioId, Visibilidade.PUBLICA).stream()
                .map(ColecaoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColecaoAlbumResponseDTO> listarAlbunsDaColecao(Long colecaoId) {
        return colecaoAlbumRepository.findById_ColecaoIDOrderByDataInclusaoDesc(colecaoId).stream()
                .map(ColecaoAlbumResponseDTO::new)
                .toList();
    }
}