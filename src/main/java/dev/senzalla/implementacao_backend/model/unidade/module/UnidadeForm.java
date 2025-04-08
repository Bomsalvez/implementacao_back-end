package dev.senzalla.implementacao_backend.model.unidade.module;

import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.unidade.entity.Unidade}
 */
public record UnidadeForm(
        @NotNull @Size(max = 200) String unidNome,
        @NotNull @Size(max = 20) String unidSigla,
        @NotNull List<EnderecoDto> enderecos
) implements Serializable {
}