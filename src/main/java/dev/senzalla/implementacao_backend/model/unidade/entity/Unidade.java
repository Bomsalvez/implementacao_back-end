package dev.senzalla.implementacao_backend.model.unidade.entity;

import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "unidade", schema = "public", indexes = {
        @Index(name = "idx_unidade_nome", columnList = "unid_nome")
})
public class Unidade {
    @Id
    @ColumnDefault("nextval('seq_unidade_id')")
    @Column(name = "unid_id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "unid_nome", nullable = false, length = 200)
    private String unidNome;

    @Size(max = 20)
    @NotNull
    @Column(name = "unid_sigla", nullable = false, length = 20)
    private String unidSigla;

    @ManyToMany
    @JoinTable(
            name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "unid_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private List<Endereco> enderecos = new ArrayList<>();

}