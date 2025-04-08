package dev.senzalla.implementacao_backend.service.minio;

import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService {

    private static final Logger logger = LoggerFactory.getLogger(MinioService.class);
    
    private final MinioClient minioClient;
    private final String bucket;
    private final int expiry;

    public MinioService(
            @Value("${minio.url}") String minioUrl,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey,
            @Value("${minio.bucket}") String bucket,
            @Value("${minio.expiry}") int expiry) {
        
        this.minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
        
        this.bucket = bucket;
        this.expiry = expiry;
        
        try {
            // Verifica se o bucket existe, se não, cria
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                logger.info("Bucket '{}' não encontrado, criando...", bucket);
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                logger.info("Bucket '{}' criado com sucesso", bucket);
            } else {
                logger.info("Bucket '{}' já existe", bucket);
            }
        } catch (Exception e) {
            logger.error("Erro ao inicializar o MinIO", e);
            throw new RuntimeException("Erro ao inicializar o MinIO", e);
        }
    }

    /**
     * Realiza o upload de uma foto para o Min.io
     * 
     * @param file O arquivo a ser enviado
     * @return O nome do objeto no Min.io
     */
    public String uploadFoto(MultipartFile file) {
        try {
            // Gera um nome único para o arquivo
            String objectName = "foto-" + UUID.randomUUID() + getFileExtension(file.getOriginalFilename());
            
            logger.info("Iniciando upload do arquivo: {}", objectName);
            
            // Faz o upload do arquivo
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            
            logger.info("Upload concluído com sucesso: {}", objectName);
            
            return objectName;
        } catch (Exception e) {
            logger.error("Erro ao fazer upload do arquivo", e);
            throw new RuntimeException("Erro ao fazer upload do arquivo", e);
        }
    }

    /**
     * Gera uma URL temporária para acesso a um objeto no Min.io
     * 
     * @param objectName O nome do objeto no Min.io
     * @return A URL temporária para acesso ao objeto
     */
    public String gerarUrlTemporaria(String objectName) {
        try {
            logger.info("Gerando URL temporária para o objeto: {}", objectName);
            
            // Verifica se o objeto existe
            minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build());
            
            // Gera uma URL temporária válida pelo tempo configurado (padrão: 5 minutos)
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .method(Method.GET)
                            .expiry(expiry, TimeUnit.SECONDS)
                            .build());
            
            logger.info("URL temporária gerada com sucesso para o objeto: {}", objectName);
            
            return url;
        } catch (Exception e) {
            logger.error("Erro ao gerar URL temporária para o objeto: {}", objectName, e);
            throw new RuntimeException("Erro ao gerar URL temporária", e);
        }
    }

    /**
     * Obtém a extensão do arquivo
     * 
     * @param filename O nome do arquivo
     * @return A extensão do arquivo
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return ".jpg"; // Extensão padrão
        }
        return filename.substring(filename.lastIndexOf("."));
    }
    
    /**
     * Verifica se um objeto existe no Min.io
     * 
     * @param objectName O nome do objeto
     * @return true se o objeto existe, false caso contrário
     */
    public boolean objectExists(String objectName) {
        try {
            minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Remove um objeto do Min.io
     * 
     * @param objectName O nome do objeto
     */
    public void removeObject(String objectName) {
        try {
            logger.info("Removendo objeto: {}", objectName);
            
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build());
            
            logger.info("Objeto removido com sucesso: {}", objectName);
        } catch (Exception e) {
            logger.error("Erro ao remover objeto: {}", objectName, e);
            throw new RuntimeException("Erro ao remover objeto", e);
        }
    }
} 