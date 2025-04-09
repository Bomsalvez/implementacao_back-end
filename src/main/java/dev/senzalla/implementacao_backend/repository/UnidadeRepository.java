package dev.senzalla.implementacao_backend.repository;

import dev.senzalla.implementacao_backend.model.unidade.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
} 