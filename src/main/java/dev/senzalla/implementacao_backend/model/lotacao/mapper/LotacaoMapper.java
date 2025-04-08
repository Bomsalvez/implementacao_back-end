package dev.senzalla.implementacao_backend.model.lotacao.mapper;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceEntityMapper;
import dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoForm;

public class LotacaoMapper implements InterfaceEntityMapper<Lotacao, LotacaoForm, LotacaoDto> {
    @Override
    public Lotacao toEntity(LotacaoForm dto) {
        return null;
    }

    @Override
    public LotacaoDto toDto(Lotacao entity) {
        return null;
    }
}
