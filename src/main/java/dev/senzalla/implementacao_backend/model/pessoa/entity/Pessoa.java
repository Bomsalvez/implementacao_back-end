package dev.senzalla.implementacao_backend.model.pessoa.entity;

import dev.senzalla.implementacao_backend.model.endereco.entity.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pessoa", schema = "public", indexes = {
        @Index(name = "idx_pessoa_nome", columnList = "pes_nome")
})
public class Pessoa {
    @Id
    @ColumnDefault("nextval('seq_pessoa_id')")
    @Column(name = "pes_id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "pes_nome", nullable = false, length = 200)
    private String pesNome;

    @NotNull
    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate pesDataNascimento;

    @Size(max = 1)
    @NotNull
    @Column(name = "pes_sexo", nullable = false, length = 1)
    private String pesSexo;

    @Size(max = 200)
    @NotNull
    @Column(name = "pes_mae", nullable = false, length = 200)
    private String pesMae;

    @Size(max = 200)
    @Column(name = "pes_pai", length = 200)
    private String pesPai;

    @ManyToMany
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private List<Endereco> enderecos = new ArrayList<>();

}