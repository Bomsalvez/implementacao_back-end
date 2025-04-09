package dev.senzalla.implementacao_backend.model.lotacao.mapper;

import dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoForm;
import dev.senzalla.implementacao_backend.model.pessoa.mapper.PessoaMapper;
import dev.senzalla.implementacao_backend.model.unidade.mapper.UnidadeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LotacaoMapper  {
    
    private final PessoaMapper pessoaMapper;
    private final UnidadeMapper unidadeMapper;
    
    public Lotacao toEntity(LotacaoForm form) {
        if (form == null) {
            return null;
        }
        
        Lotacao lotacao = new Lotacao();
        lotacao.setPes(pessoaMapper.toEntity(form.pes()));
        lotacao.setUnid(unidadeMapper.toEntity(form.unid()));
        lotacao.setLotDataLotacao(form.lotDataLotacao());
        lotacao.setLotDataRemocao(form.lotDataRemocao());
        lotacao.setLotPortaria(form.lotPortaria());
        
        return lotacao;
    }

    public LotacaoDto toDto(Lotacao entity) {
        if (entity == null) {
            return null;
        }
        
        return new LotacaoDto(
            entity.getId(),
            pessoaMapper.toDto(entity.getPes()),
            unidadeMapper.toDto(entity.getUnid()),
            entity.getLotDataLotacao(),
            entity.getLotDataRemocao(),
            entity.getLotPortaria()
        );
    }

   
    public Collection<LotacaoDto> toDto(Iterable<Lotacao> entities) {
        if (entities == null) {
            return Page.<LotacaoDto>empty().getContent();
        }
        
        if (entities instanceof Page<Lotacao> page) {
            return new PageImpl<>(page.getContent().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList()), page.getPageable(), page.getTotalElements())
                    .getContent();
        }
        
        return ((Collection<Lotacao>) entities).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
