package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.ArtistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<ArtistaModel, Long> {

    List<ArtistaModel> findByNomeArtisticoContainingIgnoreCase(String nomeArtistico);

    List<ArtistaModel> findByNacionalidadeIgnoreCase(String nacionalidade);

    boolean existsByNomeArtisticoIgnoreCase(String nomeArtistico);


    List<ArtistaModel> findByAnoInicioCarreiraGreaterThanEqualOrderByAnoInicioCarreiraAsc(int ano);

    long countByNacionalidadeIgnoreCase(String nacionalidade);

    @Query("SELECT a FROM ArtistaModel a WHERE TYPE(a) = :tipo")
    List<ArtistaModel> findByTipoArtista(@Param("tipo") Class<? extends ArtistaModel> tipo);
}