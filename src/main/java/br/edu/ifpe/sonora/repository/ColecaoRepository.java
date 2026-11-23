package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.ColecaoModel;
import br.edu.ifpe.sonora.model.Visibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ColecaoRepository extends JpaRepository<ColecaoModel, Long> {

    List<ColecaoModel> findByUsuario_IdOrderByDataCriacaoDesc(Long usuarioId);

    List<ColecaoModel> findByUsuario_IdAndVisibilidade(Long usuarioId, Visibilidade visibilidade);

    List<ColecaoModel> findByNomeContainingIgnoreCaseAndVisibilidade(String nome, Visibilidade visibilidade);


    long countByVisibilidade(Visibilidade visibilidade);

    List<ColecaoModel> findByDataCriacaoBetween(LocalDate dataInicio, LocalDate dataFim);


    @Query("SELECT c FROM ColecaoModel c LEFT JOIN c.itens i GROUP BY c ORDER BY COUNT(i) DESC")
    List<ColecaoModel> findTopColecoesComMaisAlbuns();
}