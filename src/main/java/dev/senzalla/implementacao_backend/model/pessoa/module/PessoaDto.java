package dev.senzalla.implementacao_backend.model.pessoa.module;

import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa}
 */
public record PessoaDto(
        Integer id,
        String pesNome,
        LocalDate pesDataNascimento,
        String pesSexo,
        String pesMae,
        String pesPai,
        List<EnderecoDto> enderecos
) implements Serializable {
}