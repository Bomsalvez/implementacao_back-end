package dev.senzalla.implementacao_backend.model.endereco.module;

import dev.senzalla.implementacao_backend.model.cidade.module.CidadeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.endereco.entity.Endereco}
 */
public record EnderecoForm(
        @NotNull @Size(max = 50) String endTipoLogradouro,
        @NotNull @Size(max = 200) String endLogradouro,
        @NotNull Integer endNumero,
        @NotNull @Size(max = 100) String endBairro,
        @NotNull CidadeDto cid
) implements Serializable {
}