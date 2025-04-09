package dev.senzalla.implementacao_backend.core.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterfaceService<E, F, D> {
    D create(F form);
    D findById(Integer id);
    Page<D> findAll(Pageable pageable);
    D update(Integer id, F form);
    void delete(Integer id);
} 