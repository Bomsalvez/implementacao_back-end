package dev.senzalla.implementacao_backend.service.jwt;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtService {

    private final JwtTokenGenerator tokenGenerator;
    private final JwtTokenValidator tokenValidator;

    public String generateToken(String username) {
        return tokenGenerator.generateToken(username);
    }

    public String generateRefreshToken(String username) {
        return tokenGenerator.generateRefreshToken(username);
    }

    public String validateToken(String token) {
        return tokenValidator.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return tokenValidator.getUsernameFromToken(token);
    }
} 