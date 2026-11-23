package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.SeguirID;
import br.edu.ifpe.sonora.model.SeguirModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguirRepository extends JpaRepository<SeguirModel, SeguirID> {

    List<SeguirModel> findById_SeguidorIDOrderByDataInicioDesc(Long seguidorID);

    List<SeguirModel> findById_SeguidoIDOrderByDataInicioDesc(Long seguidoID);

    long countById_SeguidoID(Long seguidoID);

    long countById_SeguidorID(Long seguidorID);
}