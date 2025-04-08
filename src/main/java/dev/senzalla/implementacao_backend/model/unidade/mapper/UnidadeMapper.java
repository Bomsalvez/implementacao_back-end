package dev.senzalla.implementacao_backend.model.unidade.mapper;

import dev.senzalla.implementacao_backend.model.endereco.dto.EnderecoDTO;
import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;
import dev.senzalla.implementacao_backend.model.unidade.dto.UnidadeDTO;
import dev.senzalla.implementacao_backend.model.unidade.entity.Unidade;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UnidadeMapper {

    public UnidadeDTO toDTO(Unidade unidade) {
        if (unidade == null) {
            return null;
        }
        
        UnidadeDTO dto = new UnidadeDTO();
        dto.setId(unidade.getId());
        dto.setNome(unidade.getUnidNome());
        dto.setSigla(unidade.getUnidSigla());
        
        if (unidade.getEnderecos() != null) {
            dto.setEnderecos(unidade.getEnderecos().stream()
                    .map(this::enderecoToDTO)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public Unidade toEntity(UnidadeDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Unidade unidade = new Unidade();
        unidade.setId(dto.getId());
        unidade.setUnidNome(dto.getNome());
        unidade.setUnidSigla(dto.getSigla());
        
        // Endereços são gerenciados separadamente
        
        return unidade;
    }
    
    private EnderecoDTO enderecoToDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setTipoLogradouro(endereco.getEndTipoLogradouro());
        dto.setLogradouro(endereco.getEndLogradouro());
        dto.setNumero(endereco.getEndNumero());
        dto.setBairro(endereco.getEndBairro());
        
        if (endereco.getCid() != null) {
            dto.setCidadeId(endereco.getCid().getId());
            dto.setCidadeNome(endereco.getCid().getCidNome());
            dto.setUf(endereco.getCid().getCidUf());
        }
        
        return dto;
    }
}
