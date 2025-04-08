package dev.senzalla.implementacao_backend.model.servidor.efetivo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivoDTO {
    private Integer id;
    private String matricula;
    private Integer pessoaId;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Integer idade;
    private Integer unidadeId;
    private String unidadeNome;
} 