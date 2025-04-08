package dev.senzalla.implementacao_backend.controller;

import dev.senzalla.implementacao_backend.model.auth.LoginDTO;
import dev.senzalla.implementacao_backend.model.auth.TokenDTO;
import dev.senzalla.implementacao_backend.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private final AuthService service;


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
       TokenDTO tokenDTO = service.autenticar(loginDTO);
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshToken(@RequestParam String refreshToken) {
        TokenDTO tokenDTO = service.refresh(refreshToken);
        return ResponseEntity.ok(tokenDTO);
    }
}