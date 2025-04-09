package dev.senzalla.implementacao_backend.service.servidor.efetivo;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceService;
import dev.senzalla.implementacao_backend.model.pessoa.service.PessoaService;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.entity.ServidorEfetivo;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.mapper.ServidorEfetivoMapper;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.module.ServidorEfetivoDto;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.module.ServidorEfetivoForm;
import dev.senzalla.implementacao_backend.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoService implements InterfaceService<ServidorEfetivo, ServidorEfetivoForm, ServidorEfetivoDto> {

    private final ServidorEfetivoRepository repository;
    private final ServidorEfetivoMapper mapper;
    private final PessoaService pessoaService;

    @Override
    @Transactional
    public ServidorEfetivoDto create(ServidorEfetivoForm form) {
        ServidorEfetivo entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public ServidorEfetivoDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServidorEfetivoDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ServidorEfetivoDto update(Integer id, ServidorEfetivoForm form) {
        return repository.findById(id)
                .map(entity -> {
                    ServidorEfetivo updatedEntity = mapper.toEntity(form);
                    updatedEntity.setId(id);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
} 