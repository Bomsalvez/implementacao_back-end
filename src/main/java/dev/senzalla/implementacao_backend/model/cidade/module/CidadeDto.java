package dev.senzalla.implementacao_backend.model.cidade.module;

import dev.senzalla.implementacao_backend.model.cidade.entity.Cidade;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.cidade.entity.Cidade}
 */
public record CidadeDto(
        Integer id,
        String cidNome,
        String cidUf
) implements Serializable {
    public CidadeDto(@NotNull Cidade cid) {
        this (
                cid.getId(),
                cid.getCidNome(),
                cid.getCidUf()
        );
    }
}