package dev.senzalla.implementacao_backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.repository.FotoPessoaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FotoPessoaService {

    private final FotoPessoaRepository fotoPessoaRepository;
    private final MinioService minioService;


    public String buscarFotoPorId(Long id) {
        Optional<FotoPessoa> fotoPessoaOptional = fotoPessoaRepository.findById(id);
        if (fotoPessoaOptional.isPresent()) {
            FotoPessoa fotoPessoa = fotoPessoaOptional.get();
            try {
                return minioService.getTemporaryUrl(fotoPessoa.getId().toString());
            } catch (Exception e) {
                throw new RuntimeException("Erro ao gerar URL temporária", e);
            }
        } else {
            throw new RuntimeException("Foto não encontrada");
        }
    }

    public FotoPessoa salvarFoto(MultipartFile file, Long id) {
        try {
            String objectName = "foto-" + id;
            minioService.uploadFile(file, objectName);
            FotoPessoa fotoPessoa = new FotoPessoa();
            fotoPessoa.setId(id);
            return fotoPessoaRepository.save(fotoPessoa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a foto", e);
        }
    }

    public void deletarFoto(Long id) {
        fotoPessoaRepository.deleteById(id);
    }
}