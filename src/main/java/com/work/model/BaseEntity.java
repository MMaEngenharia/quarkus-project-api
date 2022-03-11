package com.work.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable, Cloneable {

	private static final long serialVersionUID = -2297041060391842484L;

	@Id
    @Column(
        columnDefinition = "bigint",  
        name = "id", 
        unique = true, 
        nullable = false, 
        updatable = false
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "id"
    )
    private ID id;

    @Column(
        columnDefinition = "character varying", 
        nullable = false, 
        updatable = false
    )
    private String uuid;

    @Column(
        columnDefinition = "timestamp with time zone",
        nullable = false,
        updatable = false
    )
    private LocalDateTime inclusao;

    @Column(
        columnDefinition = "timestamp with time zone",
        insertable = false
    )
    private LocalDateTime edicao;

    @JsonIgnore
    @Column(nullable = false)
    private boolean excluido = Boolean.FALSE;

    @JsonIgnore
    @Transient
    private int objHasCodeSuper = super.hashCode();

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getInclusao() {
        return this.inclusao;
    }

    public void setInclusao(LocalDateTime inclusao) {
        this.inclusao = inclusao;
    }

    public LocalDateTime getEdicao() {
        return this.edicao;
    }

    public void setEdicao(LocalDateTime edicao) {
        this.edicao = edicao;
    }

    public int getObjHasCodeSuper() {
        return this.objHasCodeSuper;
    }

    public void setObjHasCodeSuper(int objHasCodeSuper) {
        this.objHasCodeSuper = objHasCodeSuper;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        var entityBase = (BaseEntity<?>) o;
        return Objects.equals(id, entityBase.id) && Objects.equals(uuid, entityBase.uuid) && objHasCodeSuper == entityBase.objHasCodeSuper;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, objHasCodeSuper);
    }

    @Override
    public BaseEntity<ID> clone() {
        try {
            return (BaseEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
