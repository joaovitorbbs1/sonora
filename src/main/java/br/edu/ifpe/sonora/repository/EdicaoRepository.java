package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.EdicaoID;
import br.edu.ifpe.sonora.model.EdicaoModel;
import br.edu.ifpe.sonora.model.Formatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdicaoRepository extends JpaRepository<EdicaoModel, EdicaoID> {

    List<EdicaoModel> findByEdicaoID_AlbumIdOrderByEdicaoID_NumeroEdicaoAsc(Long albumId);

    List<EdicaoModel> findByFormato(Formatos formato);

    List<EdicaoModel> findByGravadoraContainingIgnoreCase(String gravadora);

    long countByEdicaoID_AlbumId(Long albumId);
}