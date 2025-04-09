package dev.senzalla.implementacao_backend.model.endereco.module;

import dev.senzalla.implementacao_backend.model.cidade.module.CidadeDto;
import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;

import java.io.Serializable;
import java.util.List;

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
    public EnderecoDto(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getEndTipoLogradouro(),
                endereco.getEndLogradouro(),
                endereco.getEndNumero(),
                endereco.getEndBairro(),
                new CidadeDto(endereco.getCid())
        );
    }
}