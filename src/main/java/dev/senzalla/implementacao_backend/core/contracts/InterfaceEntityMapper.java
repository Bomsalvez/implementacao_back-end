package dev.senzalla.implementacao_backend.core.contracts;

public interface InterfaceEntityMapper <E,F,D> {
    E toEntity(F dto);
    D toDto(E entity);
}
