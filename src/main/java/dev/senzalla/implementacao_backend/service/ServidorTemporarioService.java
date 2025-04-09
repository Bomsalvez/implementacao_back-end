package dev.senzalla.implementacao_backend.service;

import dev.senzalla.implementacao_backend.model.servidor.temporario.entity.ServidorTemporario;
import dev.senzalla.implementacao_backend.model.servidor.temporario.mapper.ServidorTemporarioMapper;
import dev.senzalla.implementacao_backend.model.servidor.temporario.module.ServidorTemporarioDto;
import dev.senzalla.implementacao_backend.model.servidor.temporario.module.ServidorTemporarioForm;
import dev.senzalla.implementacao_backend.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServidorTemporarioService  {

    private final ServidorTemporarioRepository repository;
    private final ServidorTemporarioMapper mapper;
    private final PessoaService pessoaService;

   
    @Transactional
    public ServidorTemporarioDto create(ServidorTemporarioForm form) {
        ServidorTemporario entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

   
    @Transactional(readOnly = true)
    public ServidorTemporarioDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }


   
    @Transactional(readOnly = true)
    public Page<ServidorTemporarioDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

   
    @Transactional
    public ServidorTemporarioDto update(Integer id, ServidorTemporarioForm form) {
        return repository.findById(id)
                .map(entity -> {
                    ServidorTemporario updatedEntity = mapper.toEntity(form);
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