package dev.senzalla.implementacao_backend.repository;

import dev.senzalla.implementacao_backend.model.servidor.temporario.entity.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Integer> {
} 