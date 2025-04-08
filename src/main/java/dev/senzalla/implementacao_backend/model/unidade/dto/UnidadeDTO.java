package dev.senzalla.implementacao_backend.model.unidade.dto;

import dev.senzalla.implementacao_backend.model.endereco.dto.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeDTO {
    private Integer id;
    private String nome;
    private String sigla;
    private List<EnderecoDTO> enderecos = new ArrayList<>();
} 