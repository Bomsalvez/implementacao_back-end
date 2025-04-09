package dev.senzalla.implementacao_backend.model.cidade.mapper;

import dev.senzalla.implementacao_backend.model.cidade.entity.Cidade;
import dev.senzalla.implementacao_backend.model.cidade.module.CidadeDto;
import dev.senzalla.implementacao_backend.model.cidade.module.CidadeForm;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper  {

   
    public Cidade toEntity(CidadeForm form) {
        if (form == null) {
            return null;
        }

        Cidade entity = new Cidade();
        entity.setCidNome(form.cidNome());
        entity.setCidUf(form.cidUf());

        return entity;
    }

   
    public CidadeDto toDto(Cidade entity) {
        if (entity == null) {
            return null;
        }

        return new CidadeDto(
                entity.getId(),
                entity.getCidNome(),
                entity.getCidUf()
        );
    }

    public Cidade toEntity(CidadeDto dto) {
        if (dto == null) {
            return null;
        }

        Cidade entity = new Cidade();
        entity.setId(dto.id());
        entity.setCidNome(dto.cidNome());
        entity.setCidUf(dto.cidUf());

        return entity;
    }
}
