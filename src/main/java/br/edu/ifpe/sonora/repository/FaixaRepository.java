package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.FaixaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaixaRepository extends JpaRepository<FaixaModel, Long> {

    List<FaixaModel> findByEdicao_EdicaoID_AlbumIdAndEdicao_EdicaoID_NumeroEdicaoOrderByOrdemAsc(
            Long albumId, Long numeroEdicao);


    List<FaixaModel> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT COALESCE(SUM(f.duracao), 0) FROM FaixaModel f " +
            "WHERE f.edicao.edicaoID.albumId = :albumId AND f.edicao.edicaoID.numeroEdicao = :numeroEdicao")
    int calcularDuracaoTotalEdicao(@Param("albumId") Long albumId, @Param("numeroEdicao") Long numeroEdicao);

}