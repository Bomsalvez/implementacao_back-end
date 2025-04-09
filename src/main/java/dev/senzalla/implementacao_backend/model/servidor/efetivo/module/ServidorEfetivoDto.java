package dev.senzalla.implementacao_backend.model.servidor.efetivo.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;

import java.io.Serializable;

public record ServidorEfetivoDto(
        Integer id,
        String seMatricula,
        PessoaDto pessoa
) implements Serializable {
} 