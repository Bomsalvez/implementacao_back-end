package dev.senzalla.implementacao_backend.model.cidade.module;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.cidade.entity.Cidade}
 */
public record CidadeDto(Integer id, String cidNome, String cidUf) implements Serializable {
}