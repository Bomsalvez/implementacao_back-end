package dev.senzalla.implementacao_backend.repository;

import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
    Optional<FotoPessoa> findByPessoaAndFpPrincipal(Pessoa pessoa, boolean principal);
} 