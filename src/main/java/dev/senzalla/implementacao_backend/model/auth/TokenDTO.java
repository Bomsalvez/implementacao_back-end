package dev.senzalla.implementacao_backend.model.auth;

public record TokenDTO (
     String token,
     String refreshToken
){
} 