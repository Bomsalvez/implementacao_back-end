package dev.senzalla.implementacao_backend.model.pessoa.module;

import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa}
 */
public record PessoaForm(
        @NotNull @Size(max = 200) String pesNome,
        @NotNull LocalDate pesDataNascimento,
        @NotNull @Size(max = 1) String pesSexo,
        @NotNull @Size(max = 200) String pesMae,
        @Size(max = 200) String pesPai,
        @NotNull List<EnderecoDto> enderecos
) implements Serializable {
}