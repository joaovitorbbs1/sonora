package br.edu.ifpe.sonora.service;

import br.edu.ifpe.sonora.dto.FaixaRequestDTO;
import br.edu.ifpe.sonora.dto.FaixaResponseDTO;
import br.edu.ifpe.sonora.model.EdicaoID;
import br.edu.ifpe.sonora.model.EdicaoModel;
import br.edu.ifpe.sonora.model.FaixaModel;
import br.edu.ifpe.sonora.repository.EdicaoRepository;
import br.edu.ifpe.sonora.repository.FaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaixaService {

    private final FaixaRepository faixaRepository;
    private final EdicaoRepository edicaoRepository;

    public FaixaService(FaixaRepository faixaRepository, EdicaoRepository edicaoRepository) {
        this.faixaRepository = faixaRepository;
        this.edicaoRepository = edicaoRepository;
    }

    @Transactional
    public FaixaResponseDTO cadastrar(FaixaRequestDTO dto) {
        EdicaoID edicaoID = new EdicaoID(dto.albumId(), dto.numeroEdicao());
        EdicaoModel edicao = edicaoRepository.findById(edicaoID)
                .orElseThrow(() -> new IllegalArgumentException("Edição não encontrada para o álbum informado."));

        FaixaModel faixa = new FaixaModel(
                dto.titulo(),
                dto.ordem(),
                dto.duracao(),
                edicao
        );

        FaixaModel salva = faixaRepository.save(faixa);
        return new FaixaResponseDTO(salva);
    }

    @Transactional(readOnly = true)
    public List<FaixaResponseDTO> listarTracklist(Long albumId, Long numeroEdicao) {
        return faixaRepository
                .findByEdicao_EdicaoID_AlbumIdAndEdicao_EdicaoID_NumeroEdicaoOrderByOrdemAsc(albumId, numeroEdicao)
                .stream()
                .map(FaixaResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public int obterDuracaoTotalEmSegundos(Long albumId, Long numeroEdicao) {
        return faixaRepository.calcularDuracaoTotalEdicao(albumId, numeroEdicao);
    }
}