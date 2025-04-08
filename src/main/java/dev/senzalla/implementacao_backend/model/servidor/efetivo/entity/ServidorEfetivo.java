package dev.senzalla.implementacao_backend.model.servidor.efetivo.entity;

import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servidor_efetivo", schema = "public")
public class ServidorEfetivo {
    @Id
    @Column(name = "pes_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Size(max = 20)
    @NotNull
    @Column(name = "se_matricula", nullable = false, length = 20)
    private String seMatricula;

}