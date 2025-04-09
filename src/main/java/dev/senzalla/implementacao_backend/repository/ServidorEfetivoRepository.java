package dev.senzalla.implementacao_backend.repository;

import dev.senzalla.implementacao_backend.model.servidor.efetivo.entity.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Integer> {
} 