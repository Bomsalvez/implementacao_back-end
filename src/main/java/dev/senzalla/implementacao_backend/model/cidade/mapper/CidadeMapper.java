package dev.senzalla.implementacao_backend.model.cidade.mapper;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceEntityMapper;
import dev.senzalla.implementacao_backend.model.cidade.entity.Cidade;
import dev.senzalla.implementacao_backend.model.cidade.module.CidadeDto;
import dev.senzalla.implementacao_backend.model.cidade.module.CidadeForm;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper implements InterfaceEntityMapper<Cidade, CidadeForm, CidadeDto> {
    @Override
    public Cidade toEntity(CidadeForm dto) {
        return null;
    }

    @Override
    public CidadeDto toDto(Cidade entity) {
        return null;
    }
}
