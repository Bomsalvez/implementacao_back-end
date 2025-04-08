package dev.senzalla.implementacao_backend.model.servidor.efetivo.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivoDTO {
    private Integer id;
    private String matricula;
    private Integer pessoaId;
    private String pessoaNome;
    private String pessoaCpf;
    private String pessoaDataNascimento;
} 