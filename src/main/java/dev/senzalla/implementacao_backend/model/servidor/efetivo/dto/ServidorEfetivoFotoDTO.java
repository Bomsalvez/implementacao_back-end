package dev.senzalla.implementacao_backend.model.servidor.efetivo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivoFotoDTO {
    private Integer id;
    private String nome;
    private Integer idade;
    private String unidadeNome;
    private List<String> urlsFotos;
} 