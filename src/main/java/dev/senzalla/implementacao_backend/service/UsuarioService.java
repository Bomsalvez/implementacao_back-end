package dev.senzalla.implementacao_backend.service;

import dev.senzalla.implementacao_backend.model.usuario.entity.Usuario;
import dev.senzalla.implementacao_backend.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByMatricula(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return usuario.get();
    }

    public Usuario criarUsuario(String username, String password) {
        if (usuarioRepository.findByMatricula(username).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        Usuario usuario = new Usuario();
        usuario.setMatricula(username);

        String bcryptPassword = passwordEncoder.encode(password);
        usuario.setSenha(bcryptPassword);

        return usuarioRepository.save(usuario);
    }

    public String getBCryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
} 