package dev.senzalla.implementacao_backend.model.lotacao.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao}
 */
public record LotacaoForm(
        @NotNull PessoaDto pes,
        @NotNull UnidadeDto unid,
        @NotNull LocalDate lotDataLotacao,
        LocalDate lotDataRemocao,
        @NotNull @Size(max = 100) String lotPortaria
) implements Serializable {
}