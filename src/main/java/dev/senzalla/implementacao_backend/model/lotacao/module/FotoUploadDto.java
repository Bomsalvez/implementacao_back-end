package dev.senzalla.implementacao_backend.model.lotacao.module;

import org.springframework.web.multipart.MultipartFile;

public record FotoUploadDto(
    Integer idServidor,
    MultipartFile foto
) {} 