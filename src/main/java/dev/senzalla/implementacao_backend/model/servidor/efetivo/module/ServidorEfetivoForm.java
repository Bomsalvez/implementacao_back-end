package dev.senzalla.implementacao_backend.model.servidor.efetivo.module;

import dev.senzalla.implementacao_backend.model.pessoa.module.PessoaDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ServidorEfetivoForm(
        @NotNull PessoaDto pessoa,
        @NotNull @Size(max = 20) String seMatricula
) implements Serializable {
} 