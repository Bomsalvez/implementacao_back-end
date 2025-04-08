package dev.senzalla.implementacao_backend.model.usuario.entity;

import dev.senzalla.implementacao_backend.model.servidor.efetivo.entity.ServidorEfetivo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario", schema = "public")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_id")
    @SequenceGenerator(name = "seq_usuario_id", sequenceName = "seq_usuario_id", allocationSize = 1)
    @Column(name = "usr_id", nullable = false)
    private Integer id;

    @Column(name = "usr_matricula", nullable = false, unique = true, length = 20)
    private String matricula;

    @Column(name = "usr_senha", nullable = false)
    private String senha;

    @Column(name = "usr_ativo")
    private boolean ativo = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "se_id")
    private ServidorEfetivo servidorEfetivo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return matricula;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}