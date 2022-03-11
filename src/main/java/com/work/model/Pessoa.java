package com.work.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pessoa")
@SequenceGenerator(
    name = "id",
    sequenceName = "pessoa_id_seq",
    allocationSize = 1
)
public class Pessoa extends BaseEntity<Long> {

    private static final long serialVersionUID = -7565499446202587565L;

    @Column(columnDefinition = "character varying")
    private String codigo;

    @NotBlank
    @NotNull
    @Column(
        columnDefinition = "character varying",
        length = 100,
        nullable = false
    )
    private String nome;

    @Column(columnDefinition = "timestamp with time zone")
    private LocalDate nascimento;

    @Valid
    @OrderBy
    @JsonManagedReference("pessoa_endereco")
    @OneToMany(
        mappedBy = "pessoa",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<PessoaEndereco> enderecos;

    private Pessoa(Builder builder) {
        super.setId(builder.getId());
        super.setUuid(builder.getUuid());
        super.setInclusao(builder.getInclusao());
        super.setEdicao(builder.getEdicao());
        codigo = builder.codigo;
        nome = builder.nome;
        nascimento = builder.nascimento;
        enderecos = builder.enderecos;
    }

    public Pessoa() {
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<PessoaEndereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<PessoaEndereco> enderecos) {
        this.enderecos = enderecos;
    }

    public static class Builder extends BaseEntity<Long> {

        private String codigo;

        private String nome;

        private LocalDate nascimento;

        private List<PessoaEndereco> enderecos;

        public Builder id(Long id) {
            super.setId(id);
            return this;
        }

        public Builder uuid(String uuid) {
            super.setUuid(uuid);
            return this;
        }

        public Builder inclucao(LocalDateTime inclusao) {
            super.setInclusao(inclusao);
            return this;
        }

        public Builder edicao(LocalDateTime edicao) {
            super.setEdicao(edicao);
            return this;
        }

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        private Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        private Builder nascimento(LocalDate nascimento) {
            this.nascimento = nascimento;
            return this;
        }

        private Builder enderecos(List<PessoaEndereco> enderecos) {
            this.enderecos = enderecos;
            return this;
        }

        public Pessoa build() {
            return new Pessoa(this);
        }
    }
}
