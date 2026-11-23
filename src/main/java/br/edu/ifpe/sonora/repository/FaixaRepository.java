package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.FaixaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaixaRepository extends JpaRepository<FaixaModel, Long> {

    List<FaixaModel> findByEdicao_EdicaoID_AlbumIdAndEdicao_EdicaoID_NumeroEdicaoOrderByOrdemAsc(
            Long albumId, Long numeroEdicao);


    List<FaixaModel> findByTituloContainingIgnoreCase(String titulo);

}