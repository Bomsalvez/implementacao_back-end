package dev.senzalla.implementacao_backend.model.servidor.temporario.entity;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "servidor_temporario", schema = "public")
public class ServidorTemporario {
    @Id
    @Column(name = "pes_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @NotNull
    @Column(name = "st_data_admissao", nullable = false)
    private LocalDate stDataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate stDataDemissao;

}