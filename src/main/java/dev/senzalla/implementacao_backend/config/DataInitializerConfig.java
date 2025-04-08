package dev.senzalla.implementacao_backend.config;

import dev.senzalla.implementacao_backend.model.usuario.entity.Usuario;
import dev.senzalla.implementacao_backend.repository.UsuarioRepository;
import dev.senzalla.implementacao_backend.service.usuario.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializerConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializerConfig.class);

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializerConfig(
            UsuarioService usuarioService,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        try {
            Optional<Usuario> adminUsuario = usuarioRepository.findByMatricula("admin");
            
            if (adminUsuario.isEmpty()) {
                // Criar usuário admin para testes
                logger.info("Criando usuário admin para testes...");
                Usuario usuario = new Usuario();
                usuario.setMatricula("admin");
                usuario.setSenha(passwordEncoder.encode("123456"));
                usuario.setAtivo(true);
                
                usuarioRepository.save(usuario);
                logger.info("Usuário admin criado com sucesso.");
            } else {
                logger.info("Usuário admin já existe.");
                
                // Verificar se a senha está no formato BCrypt
                Usuario admin = adminUsuario.get();
                if (!admin.getSenha().startsWith("$2a$") && !admin.getSenha().startsWith("$2b$")) {
                    admin.setSenha(passwordEncoder.encode("123456"));
                    usuarioRepository.save(admin);
                    logger.info("Senha do usuário admin atualizada para formato BCrypt.");
                }
            }
            
            // Exibir senha criptografada para debug
            logger.info("Senha '123456' codificada: {}", passwordEncoder.encode("123456"));
            
        } catch (Exception e) {
            logger.error("Erro ao criar usuário admin: {}", e.getMessage(), e);
        }
    }
} 