package dev.senzalla.implementacao_backend.model.endereco.mapper;

import dev.senzalla.implementacao_backend.model.cidade.mapper.CidadeMapper;
import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;
import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;
import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoMapper  {

    private final CidadeMapper cidadeMapper;

   
    public Endereco toEntity(EnderecoForm form) {
        if (form == null) {
            return null;
        }

        Endereco entity = new Endereco();
        entity.setEndTipoLogradouro(form.endTipoLogradouro());
        entity.setEndLogradouro(form.endLogradouro());
        entity.setEndNumero(form.endNumero());
        entity.setEndBairro(form.endBairro());
        entity.setCid(cidadeMapper.toEntity(form.cid()));

        return entity;
    }

   
    public EnderecoDto toDto(Endereco entity) {
        if (entity == null) {
            return null;
        }

        return new EnderecoDto(
                entity.getId(),
                entity.getEndTipoLogradouro(),
                entity.getEndLogradouro(),
                entity.getEndNumero(),
                entity.getEndBairro(),
                cidadeMapper.toDto(entity.getCid())
        );
    }
}
