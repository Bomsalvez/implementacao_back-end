package dev.senzalla.implementacao_backend.model.unidade.module;

import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.unidade.entity.Unidade}
 */
public record UnidadeDto(
        Integer id,
        String unidNome,
        String unidSigla,
        List<EnderecoDto> enderecos
) implements Serializable {
}