package dev.senzalla.implementacao_backend.model.unidade.mapper;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceCollectionMapper;
import dev.senzalla.implementacao_backend.core.contracts.InterfaceEntityMapper;
import dev.senzalla.implementacao_backend.model.endereco.mapper.EnderecoMapper;
import dev.senzalla.implementacao_backend.model.unidade.entity.Unidade;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeDto;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UnidadeMapper implements InterfaceEntityMapper<Unidade, UnidadeForm, UnidadeDto> , InterfaceCollectionMapper<Unidade, UnidadeDto> {
    private final EnderecoMapper enderecoMapper;

    @Override
    public UnidadeDto toDto(Unidade unidade) {
        if (unidade == null) {
            return null;
        }

        var enderecos = unidade.getEnderecos() != null
            ? unidade.getEnderecos().stream()
                .map(enderecoMapper::toDto)
                .collect(Collectors.toList())
            : null;

        return new UnidadeDto(
            unidade.getId(),
            unidade.getUnidNome(),
            unidade.getUnidSigla(),
            enderecos
        );
    }

    @Override
    public Unidade toEntity(UnidadeForm dto) {
        if (dto == null) {
            return null;
        }

        Unidade unidade = new Unidade();
        unidade.setUnidNome(dto.unidNome());
        unidade.setUnidSigla(dto.unidSigla());


        return unidade;
    }


    @Override
    public Collection<UnidadeDto> toDto(Iterable<Unidade> entities) {
        return List.of();
    }

    public Unidade toEntity(UnidadeDto dto) {
        if (dto == null) {
            return null;
        }

        Unidade unidade = new Unidade();
        unidade.setId(dto.id());
        unidade.setUnidNome(dto.unidNome());
        unidade.setUnidSigla(dto.unidSigla());

        return unidade;
    }
}