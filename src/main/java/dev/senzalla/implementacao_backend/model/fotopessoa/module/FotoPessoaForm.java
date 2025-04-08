package dev.senzalla.implementacao_backend.model.fotopessoa.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa}
 */
public record FotoPessoaForm(@NotNull PessoaDto pes, @NotNull LocalDate fpData,
                             @NotNull @Size(max = 50) String fpBucket,
                             @NotNull @Size(max = 50) String fpHash) implements Serializable {
}