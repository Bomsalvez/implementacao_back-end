package dev.senzalla.implementacao_backend.model.fotopessoa.service;

import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.repository.FotoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoPessoaService {

    private final FotoPessoaRepository fotoPessoaRepository;

    @Autowired
    public FotoPessoaService(FotoPessoaRepository fotoPessoaRepository) {
        this.fotoPessoaRepository = fotoPessoaRepository;
    }

    public List<FotoPessoa> listarTodasFotos() {
        return fotoPessoaRepository.findAll();
    }

    public Optional<FotoPessoa> buscarFotoPorId(Long id) {
        return fotoPessoaRepository.findById(id);
    }

    public FotoPessoa salvarFoto(FotoPessoa fotoPessoa) {
        return fotoPessoaRepository.save(fotoPessoa);
    }

    public void deletarFoto(Long id) {
        fotoPessoaRepository.deleteById(id);
    }
} 