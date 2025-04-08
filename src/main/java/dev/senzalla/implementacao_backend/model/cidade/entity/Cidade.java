package dev.senzalla.implementacao_backend.model.cidade.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "cidade", schema = "public")
public class Cidade {
    @Id
    @ColumnDefault("nextval('seq_cidade_id')")
    @Column(name = "cid_id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "cid_nome", nullable = false, length = 200)
    private String cidNome;

    @Size(max = 2)
    @NotNull
    @Column(name = "cid_uf", nullable = false, length = 2)
    private String cidUf;

}