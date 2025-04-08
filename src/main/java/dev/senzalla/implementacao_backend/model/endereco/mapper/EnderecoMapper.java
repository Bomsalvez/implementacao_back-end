package dev.senzalla.implementacao_backend.model.endereco.mapper;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceCollectionMapper;
import dev.senzalla.implementacao_backend.core.contracts.InterfaceEntityMapper;
import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;
import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;
import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoForm;

import java.util.Collection;
import java.util.List;

public class EnderecoMapper implements InterfaceEntityMapper<Endereco, EnderecoForm, EnderecoDto>, InterfaceCollectionMapper<Endereco,EnderecoDto> {
    @Override
    public Endereco toEntity(EnderecoForm dto) {
        return null;
    }

    @Override
    public EnderecoDto toDto(Endereco entity) {
        return null;
    }

    @Override
    public Collection<EnderecoDto> toDto(Iterable<Endereco> entities) {
        return List.of();
    }
}
