package dev.senzalla.implementacao_backend.model.fotopessoa.entity;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entidade que representa uma foto associada a uma pessoa
 */
@Getter
@Setter
@Entity
@Table(name = "foto_pessoa", schema = "public", indexes = {
        @Index(name = "idx_fotopessoa_pessoa", columnList = "pes_id")
})
public class FotoPessoa {
    @Id
    @ColumnDefault("nextval('seq_fotopessoa_id')")
    @Column(name = "fp_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Size(max = 255)
    @NotNull
    @Column(name = "fp_url", nullable = false)
    private String fpUrl;

    @CreationTimestamp
    @Column(name = "fp_data_cadastro", nullable = false)
    private LocalDateTime fpDataCadastro;

    @Column(name = "fp_principal")
    private Boolean fpPrincipal = false;
}