package br.edu.ifpe.sonora.repository;

import br.edu.ifpe.sonora.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByUser(String user);

    Optional<UsuarioModel> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUser(String user);

    List<UsuarioModel> findByUserContainingIgnoreCaseOrPrimeiroNomeContainingIgnoreCase(String user, String primeiroNome);

    long countByEstadoIgnoreCase(String estado);
}