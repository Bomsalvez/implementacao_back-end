package dev.senzalla.implementacao_backend.model.fotopessoa.mapper;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceEntityMapper;
import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.model.fotopessoa.module.FotoPessoaDto;
import dev.senzalla.implementacao_backend.model.fotopessoa.module.FotoPessoaForm;

public class FotoPessoaMapper implements InterfaceEntityMapper<FotoPessoa, FotoPessoaForm, FotoPessoaDto> {
    @Override
    public FotoPessoa toEntity(FotoPessoaForm dto) {
        return null;
    }

    @Override
    public FotoPessoaDto toDto(FotoPessoa entity) {
        return null;
    }
}
