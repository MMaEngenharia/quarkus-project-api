package com.work.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa_endereco")
@SequenceGenerator(
    name = "id",
    sequenceName = "pessoa_endereco_id_seq",
    allocationSize = 1
)
public class PessoaEndereco extends BaseEntity<Long> {

    @Column(columnDefinition = "character varying")
    private String logradouro;

    @Column(
        columnDefinition = "character varying",
        length = 10
    )
    private String numero;

    @Column(columnDefinition = "character varying")
    private String bairro;

    @Column(columnDefinition = "character varying")
    private String cidade;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference("pessoa_endereco")
    @JoinColumn(
        name = "pessoa_id",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "pessoa_endereco_pessoa_fkey")
    )
    private Pessoa pessoa;

    private PessoaEndereco(Builder builder) {
        super.setId(builder.getId());
        super.setUuid(builder.getUuid());
        super.setInclusao(builder.getInclusao());
        super.setEdicao(builder.getEdicao());
        logradouro = builder.logradouro;
        numero = builder.numero;
        bairro = builder.bairro;
        cidade = builder.cidade;
        pessoa = builder.pessoa;
    }

    public PessoaEndereco() {
        super();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public static class Builder extends BaseEntity<Long> {

        private String logradouro;

        private String numero;

        private String bairro;

        private String cidade;

        private Pessoa pessoa;

        public Builder(Pessoa pessoa) {
            this.pessoa = pessoa;
        }

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

        public Builder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public Builder numero(String numero) {
            this.numero = numero;
            return this;
        }

        public Builder bairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public Builder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public PessoaEndereco build() {
            return new PessoaEndereco(this);
        }
    }
}
