package dev.senzalla.implementacao_backend.service;

import dev.senzalla.implementacao_backend.model.endereco.module.EnderecoDto;
import dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao;
import dev.senzalla.implementacao_backend.model.lotacao.mapper.LotacaoMapper;
import dev.senzalla.implementacao_backend.model.lotacao.module.EnderecoFuncionalDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoForm;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoServidorDto;
import dev.senzalla.implementacao_backend.repository.LotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LotacaoService  {

    private final LotacaoRepository repository;
    private final LotacaoMapper mapper;
    private final FotoPessoaService fotoPessoaService;

    @Transactional
    public LotacaoDto create(LotacaoForm form) {
        Lotacao entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public LotacaoDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<LotacaoDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Transactional
    public LotacaoDto update(Integer id, LotacaoForm form) {
        return repository.findById(id)
                .map(entity -> {
                    Lotacao updatedEntity = mapper.toEntity(form);
                    updatedEntity.setId(id);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .orElse(null);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Page<LotacaoServidorDto> findServidoresLotadosPorUnidade(Integer unidadeId, Pageable pageable) {
        Page<Lotacao> lotacoes = repository.findServidoresLotadosPorUnidade(unidadeId, pageable);
        return lotacoes.map(lotacao -> {
            String nomeServidor = lotacao.getPes().getPesNome();
            Integer idade = calcularIdade(lotacao.getPes().getPesDataNascimento());
            String nomeUnidade = lotacao.getUnid().getUnidNome();
            String fotografia = fotoPessoaService.buscarFotoPorId(Long.valueOf(lotacao.getPes().getId()));

            return new LotacaoServidorDto(
                nomeServidor,
                idade,
                nomeUnidade,
                fotografia
            );
        });
    }

    private Integer calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return null;
        }
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    @Transactional(readOnly = true)
    public Page<EnderecoFuncionalDto> findEnderecoFuncionalPorNomeServidor(String nomeServidor, Pageable pageable) {
        Page<Lotacao> lotacoes = repository.findEnderecoFuncionalPorNomeServidor(nomeServidor, pageable);
        return lotacoes.map(this::mapearParaEnderecoFuncionalDto);
    }

    private EnderecoFuncionalDto mapearParaEnderecoFuncionalDto(Lotacao lotacao) {
        String nomeServidor = lotacao.getPes().getPesNome();
        String nomeUnidade = lotacao.getUnid().getUnidNome();
        List<EnderecoDto> enderecosUnidade = extrairEnderecosUnidade(lotacao);

        return new EnderecoFuncionalDto(
                nomeServidor,
                nomeUnidade,
                enderecosUnidade
        );
    }

    private List<EnderecoDto> extrairEnderecosUnidade(Lotacao lotacao) {
        var enderecos = lotacao.getUnid().getEnderecos();
        if (enderecos == null) {
            return List.of();
        }

        return enderecos.stream()
                .map(EnderecoDto::new)
                .collect(Collectors.toList());
    }
}