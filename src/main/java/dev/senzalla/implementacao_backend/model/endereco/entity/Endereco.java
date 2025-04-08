package dev.senzalla.implementacao_backend.model.endereco.entity;

import dev.senzalla.implementacao_backend.model.cidade.entity.Cidade;
import dev.senzalla.implementacao_backend.model.pessoa.entity.Pessoa;
import dev.senzalla.implementacao_backend.model.unidade.entity.Unidade;
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
@Table(name = "endereco", schema = "public", indexes = {
        @Index(name = "idx_endereco_cid_id", columnList = "cid_id")
})
public class Endereco {
    @Id
    @ColumnDefault("nextval('seq_endereco_id')")
    @Column(name = "end_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "end_tipo_logradouro", nullable = false, length = 50)
    private String endTipoLogradouro;

    @Size(max = 200)
    @NotNull
    @Column(name = "end_logradouro", nullable = false, length = 200)
    private String endLogradouro;

    @NotNull
    @Column(name = "end_numero", nullable = false)
    private Integer endNumero;

    @Size(max = 100)
    @NotNull
    @Column(name = "end_bairro", nullable = false, length = 100)
    private String endBairro;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cid_id", nullable = false)
    private Cidade cid;

    @ManyToMany(mappedBy = "enderecos")
    private List<Pessoa> pessoas = new ArrayList<>();

    @ManyToMany(mappedBy = "enderecos")
    private List<Unidade> unidades = new ArrayList<>();

}