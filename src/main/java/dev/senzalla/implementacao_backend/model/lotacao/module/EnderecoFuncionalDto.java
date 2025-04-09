package dev.senzalla.implementacao_backend.model.lotacao.module;

import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;
import lombok.Builder;

import java.util.List;

public record EnderecoFuncionalDto(
    String nomeServidor,
    String unidadeLotacao,
    List<EnderecoDto> endereco
) {}