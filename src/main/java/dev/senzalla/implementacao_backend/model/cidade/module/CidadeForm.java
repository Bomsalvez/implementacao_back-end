package dev.senzalla.implementacao_backend.model.cidade.module;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.cidade.entity.Cidade}
 */
public record CidadeForm(
        @Size(max = 200) @NotBlank String cidNome,
        @NotNull @Size(min = 2, max = 2) String cidUf
) implements Serializable {
}