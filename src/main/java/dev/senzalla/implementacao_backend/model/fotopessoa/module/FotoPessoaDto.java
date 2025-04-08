package dev.senzalla.implementacao_backend.model.fotopessoa.module;

import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link FotoPessoa}
 */
public record FotoPessoaDto(
        Integer id,
        PessoaDto pes,
        LocalDate fpData,
        String fpBucket,
        String fpHash
) implements Serializable {
}