package dev.senzalla.implementacao_backend.service.storage;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MinioService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file, String objectName) throws Exception {
        try {
            // Verifica se o bucket existe, se não, cria
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // Faz o upload do arquivo
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );

            return objectName;
        } catch (Exception e) {
            throw new Exception("Erro ao fazer upload do arquivo: " + e.getMessage());
        }
    }

    public String getTemporaryUrl(String objectName) throws Exception {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(5, TimeUnit.MINUTES)
                    .build()
            );
        } catch (Exception e) {
            throw new Exception("Erro ao gerar URL temporária: " + e.getMessage());
        }
    }

    public void deleteFile(String objectName) throws Exception {
        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build()
            );
        } catch (Exception e) {
            throw new Exception("Erro ao deletar arquivo: " + e.getMessage());
        }
    }
} 