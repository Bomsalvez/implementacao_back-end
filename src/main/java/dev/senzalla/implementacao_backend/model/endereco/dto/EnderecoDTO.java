package dev.senzalla.implementacao_backend.model.endereco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Integer id;
    private String tipoLogradouro;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private Integer cidadeId;
    private String cidadeNome;
    private String uf;
    
    // Concatena o endereço completo
    public String getEnderecoCompleto() {
        StringBuilder endereco = new StringBuilder();
        
        if (tipoLogradouro != null && !tipoLogradouro.isEmpty()) {
            endereco.append(tipoLogradouro).append(" ");
        }
        
        if (logradouro != null && !logradouro.isEmpty()) {
            endereco.append(logradouro);
        }
        
        if (numero != null) {
            endereco.append(", ").append(numero);
        }
        
        if (bairro != null && !bairro.isEmpty()) {
            endereco.append(" - ").append(bairro);
        }
        
        if (cidadeNome != null && !cidadeNome.isEmpty()) {
            endereco.append(", ").append(cidadeNome);
        }
        
        if (uf != null && !uf.isEmpty()) {
            endereco.append("/").append(uf);
        }
        
        return endereco.toString();
    }
} 