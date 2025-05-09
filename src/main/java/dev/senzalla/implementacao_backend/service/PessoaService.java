package dev.senzalla.implementacao_backend.service;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import dev.senzalla.implementacao_backend.model.pessoa.mapper.PessoaMapper;
import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaForm;
import dev.senzalla.implementacao_backend.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaService  {

    private final PessoaRepository repository;
    private final PessoaMapper mapper;

   
    @Transactional
    public PessoaDto create(PessoaForm form) {
        Pessoa entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

   
    @Transactional(readOnly = true)
    public PessoaDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }


   
    @Transactional(readOnly = true)
    public Page<PessoaDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

   
    @Transactional
    public PessoaDto update(Integer id, PessoaForm form) {
        return repository.findById(id)
                .map(entity -> {
                    Pessoa updatedEntity = mapper.toEntity(form);
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