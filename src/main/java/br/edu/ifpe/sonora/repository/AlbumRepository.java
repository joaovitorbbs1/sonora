package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.AlbumModel;
import br.edu.ifpe.sonora.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumModel, Long> {

    List<AlbumModel> findByTituloContainingIgnoreCase(String titulo);

    List<AlbumModel> findByGenero(Genero genero);

    List<AlbumModel> findByTituloContainingIgnoreCaseAndGenero(String titulo, Genero genero);

    // Discografia completa de um artista específico (navega pelo Set<ArtistaModel> artista)
    @Query("SELECT a FROM AlbumModel a JOIN a.artista art WHERE art.id = :artistaId ORDER BY a.titulo ASC")
    List<AlbumModel> findAllByArtistaId(@Param("artistaId") Long artistaId);


    long countByGenero(Genero genero);

    // Identifica álbuns "incompletos" que ainda não possuem nenhuma edição cadastrada
    @Query("SELECT a FROM AlbumModel a WHERE a.edicoes IS EMPTY")
    List<AlbumModel> findAlbunsSemEdicao();
}