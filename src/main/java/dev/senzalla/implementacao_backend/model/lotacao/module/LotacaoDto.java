package dev.senzalla.implementacao_backend.model.lotacao.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeDto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao}
 */
public record LotacaoDto(
        Integer id,
        PessoaDto pes,
        UnidadeDto unid,
        LocalDate lotDataLotacao,
        LocalDate lotDataRemocao,
        String lotPortaria
) implements Serializable {
}