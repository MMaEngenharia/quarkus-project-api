package com.work.repository;

import com.work.model.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<Entity extends BaseEntity<Long>> implements PanacheRepository<Entity> {

    public boolean entityExists(final long id) {
        return find("id = ?1 and excluido = ?2", id, false).count() > 0;
    }

    public Optional<Entity> findById(final long id) {
        return find("id = ?1 and excluido = ?2", id, false).stream().findFirst();
    }

    public List<Entity> list() {
        return list("excluido = ?1", false);
    }

    public int delete(final long id) {
        return update("excluido = ?1, edicao = ?2 where id = ?3", true, LocalDateTime.now(), id);
    }
}
