package dev.senzalla.implementacao_backend.model.servidor.temporario.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.servidor.temporario.entity.ServidorTemporario}
 */
public record ServidorTemporarioForm(
        @NotNull PessoaDto pessoa,
        @NotNull LocalDate stDataAdmissao,
        LocalDate stDataDemissao
) implements Serializable {
}