package dev.senzalla.implementacao_backend.model.pessoa.mapper;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceEntityMapper;
import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaForm;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper implements InterfaceEntityMapper<Pessoa, PessoaForm, PessoaDto> {
    @Override
    public Pessoa toEntity(PessoaForm dto) {
        return null;
    }

    @Override
    public PessoaDto toDto(Pessoa entity) {
        return null;
    }

    public Pessoa toEntity(PessoaDto dto) {
        return null;
    }
}
