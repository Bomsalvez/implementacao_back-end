package dev.senzalla.implementacao_backend.model.fotopessoa.entity;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entidade que representa uma foto associada a uma pessoa
 */
@Entity
@Table(name = "foto_pessoa")
@Getter
@Setter
public class FotoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(name = "fp_url")
    private String fpUrl;

    @Column(name = "fp_principal")
    private boolean fpPrincipal;

    @Column(name = "fp_data")
    private LocalDate fpData;
}