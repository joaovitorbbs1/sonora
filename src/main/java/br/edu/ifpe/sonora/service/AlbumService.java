package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.model.*;
import br.edu.ifpe.sonora.dto.AlbumRequestDTO;
import br.edu.ifpe.sonora.dto.AlbumResponseDTO;
import br.edu.ifpe.sonora.repository.AlbumRepository;
import br.edu.ifpe.sonora.repository.ArtistaRepository;
import br.edu.ifpe.sonora.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;
    private final UsuarioRepository usuarioRepository;

    public AlbumService(AlbumRepository albumRepository,
                        ArtistaRepository artistaRepository,
                        UsuarioRepository usuarioRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AlbumResponseDTO cadastrar(AlbumRequestDTO dto) {
        AlbumModel album = new AlbumModel(dto.genero(), dto.titulo());

        // Busca todos os artistas pelos IDs enviados e vincula nos dois lados da relação N:N
        List<ArtistaModel> artistasEncontrados = artistaRepository.findAllById(dto.artistasIds());
        if (artistasEncontrados.size() != dto.artistasIds().size()) {
            throw new IllegalArgumentException("Um ou mais artistas informados não foram encontrados.");
        }

        artistasEncontrados.forEach(album::adicionarArtista);

        AlbumModel salvo = albumRepository.save(album);
        return new AlbumResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<AlbumResponseDTO> buscarPorGenero(Genero genero) {
        return albumRepository.findByGenero(genero).stream()
                .map(AlbumResponseDTO::new)
                .toList();
    }

    @Transactional
    public void deletarDuplicidade(Long albumId, Long usuarioAdminId) {
        UsuarioModel solicitante = usuarioRepository.findById(usuarioAdminId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário solicitante não encontrado."));

        // Regra de negócio de autorização: apenas ADMIN pode remover álbuns do catálogo
        if (solicitante.getRole() != Role.ADMIN) {
            throw new SecurityException("Acesso negado: apenas administradores podem excluir álbuns.");
        }

        if (!albumRepository.existsById(albumId)) {
            throw new IllegalArgumentException("Álbum não encontrado para exclusão.");
        }

        // Como EdicaoModel tem cascade = CascadeType.ALL e orphanRemoval = true,
        // deletar o álbum apaga automaticamente suas edições e faixas dependentes
        albumRepository.deleteById(albumId);
    }
}