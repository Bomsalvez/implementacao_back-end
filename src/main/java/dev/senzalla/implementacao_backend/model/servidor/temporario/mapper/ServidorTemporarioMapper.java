package dev.senzalla.implementacao_backend.model.servidor.temporario.mapper;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import dev.senzalla.implementacao_backend.model.pessoa.mapper.PessoaMapper;
import dev.senzalla.implementacao_backend.model.servidor.temporario.entity.ServidorTemporario;
import dev.senzalla.implementacao_backend.model.servidor.temporario.module.ServidorTemporarioDto;
import dev.senzalla.implementacao_backend.model.servidor.temporario.module.ServidorTemporarioForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServidorTemporarioMapper  {

    private final PessoaMapper pessoaMapper;

   
    public ServidorTemporario toEntity(ServidorTemporarioForm form) {
        if (form == null) {
            return null;
        }

        ServidorTemporario entity = new ServidorTemporario();
        entity.setStDataAdmissao(form.stDataAdmissao());
        entity.setStDataDemissao(form.stDataDemissao());
        
        Pessoa pessoa = pessoaMapper.toEntity(form.pessoa());
        entity.setPessoa(pessoa);
        entity.setId(pessoa.getId());

        return entity;
    }

   
    public ServidorTemporarioDto toDto(ServidorTemporario entity) {
        if (entity == null) {
            return null;
        }

        return new ServidorTemporarioDto(
                entity.getId(),
                pessoaMapper.toDto(entity.getPessoa()),
                entity.getStDataAdmissao(),
                entity.getStDataDemissao()
        );
    }
} 