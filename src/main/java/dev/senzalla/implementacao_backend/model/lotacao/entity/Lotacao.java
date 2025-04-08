package dev.senzalla.implementacao_backend.model.lotacao.entity;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import dev.senzalla.implementacao_backend.model.unidade.entity.Unidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "lotacao", schema = "public", indexes = {
        @Index(name = "idx_lotacao_pes_id", columnList = "pes_id"),
        @Index(name = "idx_lotacao_unid_id", columnList = "unid_id")
})
public class Lotacao {
    @Id
    @ColumnDefault("nextval('seq_lotacao_id')")
    @Column(name = "lot_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pes;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unid;

    @NotNull
    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate lotDataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate lotDataRemocao;

    @Size(max = 100)
    @NotNull
    @Column(name = "lot_portaria", nullable = false, length = 100)
    private String lotPortaria;

}