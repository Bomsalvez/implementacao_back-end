package dev.senzalla.implementacao_backend.model.servidor.efetivo.mapper;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import dev.senzalla.implementacao_backend.model.pessoa.mapper.PessoaMapper;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.entity.ServidorEfetivo;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.module.ServidorEfetivoDto;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.module.ServidorEfetivoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServidorEfetivoMapper  {

    private final PessoaMapper pessoaMapper;

   
    public ServidorEfetivo toEntity(ServidorEfetivoForm form) {
        if (form == null || form.pessoa() == null) {
            return null;
        }

        ServidorEfetivo entity = new ServidorEfetivo();
        entity.setSeMatricula(form.seMatricula());

        Pessoa pessoa = pessoaMapper.toEntity(form.pessoa());
        entity.setPessoa(pessoa);

        return entity;
    }

   
    public ServidorEfetivoDto toDto(ServidorEfetivo entity) {
        if (entity == null || entity.getPessoa() == null) {
            return null;
        }

        return new ServidorEfetivoDto(
                entity.getId(),
                entity.getSeMatricula(),
                pessoaMapper.toDto(entity.getPessoa())

        );
    }
}