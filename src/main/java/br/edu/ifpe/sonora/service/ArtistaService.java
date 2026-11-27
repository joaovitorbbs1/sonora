package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.dto.ArtistaResponseDTO;
import br.edu.ifpe.sonora.dto.BandaRequestDTO;
import br.edu.ifpe.sonora.dto.SoloRequestDTO;
import br.edu.ifpe.sonora.model.ArtistaModel;
import br.edu.ifpe.sonora.model.BandaModel;
import br.edu.ifpe.sonora.model.SoloModel;
import br.edu.ifpe.sonora.model.Status;
import br.edu.ifpe.sonora.repository.ArtistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Transactional
    public ArtistaResponseDTO cadastrarSolo(SoloRequestDTO dto) {
        if (artistaRepository.existsByNomeArtisticoIgnoreCase(dto.nomeArtistico())) {
            throw new IllegalArgumentException("Já existe um artista cadastrado com esse nome artístico.");
        }

        SoloModel solo = new SoloModel(
                dto.nomeArtistico(),
                dto.nacionalidade(),
                dto.biografia(),
                dto.anoInicioCarreira(),
                dto.nomeCivil(),
                dto.dataNasc()
        );

        ArtistaModel salvo = artistaRepository.save(solo);
        return ArtistaResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ArtistaResponseDTO cadastrarBanda(BandaRequestDTO dto) {
        if (artistaRepository.existsByNomeArtisticoIgnoreCase(dto.nomeArtistico())) {
            throw new IllegalArgumentException("Já existe uma banda cadastrada com esse nome.");
        }

        BandaModel banda = new BandaModel(
                dto.nomeArtistico(),
                dto.nacionalidade(),
                dto.biografia(),
                dto.anoInicioCarreira(),
                dto.statusAtividade()
        );

        ArtistaModel salva = artistaRepository.save(banda);
        return ArtistaResponseDTO.fromEntity(salva);
    }

    @Transactional(readOnly = true)
    public List<ArtistaResponseDTO> listarTodos() {
        return artistaRepository.findAll().stream()
                .map(ArtistaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ArtistaResponseDTO> buscarPorNome(String nome) {
        return artistaRepository.findByNomeArtisticoContainingIgnoreCase(nome).stream()
                .map(ArtistaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public ArtistaResponseDTO atualizarStatusBanda(Long bandaId, Status novoStatus) {
        ArtistaModel artista = artistaRepository.findById(bandaId)
                .orElseThrow(() -> new IllegalArgumentException("Artista não encontrado."));

        if (!(artista instanceof BandaModel banda)) {
            throw new IllegalArgumentException("Apenas bandas possuem status de atividade (Ativa, Inativa ou Hiato).");
        }

        banda.setStatusAtividade(novoStatus);
        return ArtistaResponseDTO.fromEntity(artistaRepository.save(banda));
    }
}