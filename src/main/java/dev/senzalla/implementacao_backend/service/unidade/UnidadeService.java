package dev.senzalla.implementacao_backend.service.unidade;

import dev.senzalla.implementacao_backend.core.contracts.InterfaceService;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeService implements InterfaceService<Unidade, UnidadeForm, UnidadeDto> {

    private final UnidadeRepository repository;
    private final UnidadeMapper mapper;

    @Override
    @Transactional
    public UnidadeDto create(UnidadeForm form) {
        Unidade entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadeDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<UnidadeDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Override
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

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
} 