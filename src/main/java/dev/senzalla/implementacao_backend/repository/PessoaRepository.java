package dev.senzalla.implementacao_backend.repository;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
} 