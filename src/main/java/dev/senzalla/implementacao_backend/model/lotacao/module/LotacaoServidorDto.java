package dev.senzalla.implementacao_backend.model.lotacao.module;

import java.time.LocalDate;

public record LotacaoServidorDto(
    String nome,
    Integer idade,
    String unidadeLotacao,
    String fotografia
) {} 