package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.dto.EdicaoRequestDTO;
import br.edu.ifpe.sonora.dto.EdicaoResponseDTO;
import br.edu.ifpe.sonora.model.AlbumModel;
import br.edu.ifpe.sonora.model.EdicaoID;
import br.edu.ifpe.sonora.model.EdicaoModel;
import br.edu.ifpe.sonora.repository.AlbumRepository;
import br.edu.ifpe.sonora.repository.EdicaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EdicaoService {

    private final EdicaoRepository edicaoRepository;
    private final AlbumRepository albumRepository;

    public EdicaoService(EdicaoRepository edicaoRepository, AlbumRepository albumRepository) {
        this.edicaoRepository = edicaoRepository;
        this.albumRepository = albumRepository;
    }

    @Transactional
    public EdicaoResponseDTO cadastrar(EdicaoRequestDTO dto) {
        AlbumModel album = albumRepository.findById(dto.albumId())
                .orElseThrow(() -> new IllegalArgumentException("Álbum não encontrado."));

        EdicaoID edicaoID = new EdicaoID(dto.albumId(), dto.numeroEdicao());
        if (edicaoRepository.existsById(edicaoID)) {
            throw new IllegalArgumentException("Já existe a edição número " + dto.numeroEdicao() + " para este álbum.");
        }

        EdicaoModel edicao = new EdicaoModel(
                album,
                dto.numeroEdicao(),
                dto.formato(),
                dto.gravadora(),
                dto.dataLancamento()
        );

        EdicaoModel salva = edicaoRepository.save(edicao);
        return new EdicaoResponseDTO(salva);
    }

    @Transactional(readOnly = true)
    public List<EdicaoResponseDTO> listarPorAlbum(Long albumId) {
        return edicaoRepository.findByEdicaoID_AlbumIdOrderByEdicaoID_NumeroEdicaoAsc(albumId).stream()
                .map(EdicaoResponseDTO::new)
                .toList();
    }
}