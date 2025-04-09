package dev.senzalla.implementacao_backend.service;

import dev.senzalla.implementacao_backend.model.auth.LoginDTO;
import dev.senzalla.implementacao_backend.model.auth.TokenDTO;
import dev.senzalla.implementacao_backend.model.usuario.entity.Usuario;
import dev.senzalla.implementacao_backend.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService tokenService;

    /**
     * Realiza a autenticação do usuário e gera tokens de acesso
     *
     * @param credenciais dados de login do usuário
     * @return objeto contendo tokens de acesso e atualização
     * @throws AuthenticationException se as credenciais forem inválidas
     */
    public TokenDTO autenticar(LoginDTO credenciais) {
        Usuario usuario = buscarUsuarioPorMatricula(credenciais.username());
        validarSenha(credenciais.password(), usuario.getSenha());
        return gerarTokens(usuario.getMatricula());
    }

    public TokenDTO refresh(String refreshToken) {
        String usuario = tokenService.validateToken(refreshToken);

        if (usuario == null) {
            throw new BadCredentialsException("Token inválida");
        }

        return gerarTokens(usuario);
    }

    private Usuario buscarUsuarioPorMatricula(String matricula) {
        return usuarioService.loadUserByUsername(matricula);
    }

    private void validarSenha(String senhaInformada, String senhaArmazenada) {
        if (!passwordEncoder.matches(senhaInformada, senhaArmazenada)) {
            throw new BadCredentialsException("Senha inválida");
        }
    }

    private TokenDTO gerarTokens(String matricula) {
        String accessToken = tokenService.generateToken(matricula);
        String refreshToken = tokenService.generateRefreshToken(matricula);
        return new TokenDTO(accessToken, refreshToken);
    }


}