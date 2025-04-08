package dev.senzalla.implementacao_backend.model.endereco.module;

import dev.senzalla.implementacao_backend.model.cidade.module.CidadeDto;
import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;

import java.io.Serializable;

/**
 * DTO for {@link Endereco}
 */
public record EnderecoDto(
        Integer id,
        String endTipoLogradouro,
        String endLogradouro,
        Integer endNumero,
        String endBairro,
        CidadeDto cid
) implements Serializable {
}