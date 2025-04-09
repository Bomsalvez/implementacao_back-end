package dev.senzalla.implementacao_backend.service;

import dev.senzalla.implementacao_backend.model.unidade.entity.Unidade;
import dev.senzalla.implementacao_backend.model.unidade.mapper.UnidadeMapper;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeDto;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeForm;
import dev.senzalla.implementacao_backend.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnidadeService  {

    private final UnidadeRepository repository;
    private final UnidadeMapper mapper;

   
    @Transactional
    public UnidadeDto create(UnidadeForm form) {
        Unidade entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

   
    @Transactional(readOnly = true)
    public UnidadeDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }


   
    @Transactional(readOnly = true)
    public Page<UnidadeDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

   
    @Transactional
    public UnidadeDto update(Integer id, UnidadeForm form) {
        return repository.findById(id)
                .map(entity -> {
                    Unidade updatedEntity = mapper.toEntity(form);
                    updatedEntity.setId(id);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .orElse(null);
    }

   
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
} 