package dev.senzalla.implementacao_backend.model.servidor.temporario.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import dev.senzalla.implementacao_backend.model.servidor.temporario.entity.ServidorTemporario;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ServidorTemporario}
 */
public record ServidorTemporarioDto(
        Integer id,
        PessoaDto pessoa,
        LocalDate stDataAdmissao,
        LocalDate stDataDemissao
) implements Serializable {
}