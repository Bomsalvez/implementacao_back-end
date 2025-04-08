package dev.senzalla.implementacao_backend.core.contracts;

import java.util.Collection;

public interface InterfaceCollectionMapper <T, U> {
    Collection<U> toDto(Iterable<T> entities);
}
