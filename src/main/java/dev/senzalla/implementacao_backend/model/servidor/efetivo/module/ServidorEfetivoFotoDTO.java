package dev.senzalla.implementacao_backend.model.servidor.efetivo.module;

import java.util.List;

public record ServidorEfetivoFotoDTO(
        Integer id,
        String nome,
        Integer idade,
        String unidadeNome,
        List<String> urlsFotos
) {
} 