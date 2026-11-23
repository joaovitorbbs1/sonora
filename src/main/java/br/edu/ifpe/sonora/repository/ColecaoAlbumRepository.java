package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.ColecaoAlbumID;
import br.edu.ifpe.sonora.model.ColecaoAlbumModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColecaoAlbumRepository extends JpaRepository<ColecaoAlbumModel, ColecaoAlbumID> {

    List<ColecaoAlbumModel> findById_ColecaoIDOrderByDataInclusaoDesc(Long colecaoID);

    List<ColecaoAlbumModel> findById_AlbumID(Long albumID);

    long countById_ColecaoID(Long colecaoID);
}