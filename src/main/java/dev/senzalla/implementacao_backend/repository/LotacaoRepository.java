package dev.senzalla.implementacao_backend.repository;

import dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao;
import dev.senzalla.implementacao_backend.model.lotacao.module.EnderecoFuncionalDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoServidorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer> {
    
    @Query("SELECT l FROM Lotacao l " +
           "JOIN l.pes p " +
           "JOIN l.unid u " +
           "WHERE l.unid.id = :unidadeId " +
           "AND l.lotDataRemocao IS NULL")
    Page<Lotacao> findServidoresLotadosPorUnidade(@Param("unidadeId") Integer unidadeId, Pageable pageable);

    @Query("SELECT l FROM Lotacao l " +
           "JOIN l.pes p " +
           "JOIN l.unid u " +
           "WHERE UPPER(p.pesNome) LIKE UPPER(CONCAT('%', :nomeServidor, '%')) " +
           "AND l.lotDataRemocao IS NULL")
    Page<Lotacao> findEnderecoFuncionalPorNomeServidor(@Param("nomeServidor") String nomeServidor, Pageable pageable);
} 