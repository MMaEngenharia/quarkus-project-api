package com.work.business;

import com.work.model.BaseEntity;
import com.work.repository.BaseRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.NoContentException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class BaseBusiness<Entity extends BaseEntity<Long>, Reposytory extends BaseRepository<Entity>> {

    @Inject
    protected Reposytory reposytory;

    public Entity findById(final long id) {
        return reposytory.findById(id)
            .orElseThrow(() -> new NotFoundException("Registro não encontrado."));
    }

    public List<Entity> listAll() throws NoContentException {
        var list = reposytory.list();
        if (list.isEmpty())
            throw new NoContentException("Registro não encontrado.");
        return list;
    }

    @Transactional
    public Entity save(Entity entity) {
        entity.setInclusao(LocalDateTime.now());
        reposytory.persist(entity);
        return entity;
    }

    @Transactional
    public Entity update(Entity entity) {
        entity.setEdicao(LocalDateTime.now());
        return reposytory.getEntityManager().merge(entity);
    }

    @Transactional
    public Entity saveOrUpdate(Entity entity) {
        return Objects.isNull(entity.getId()) ? save(entity) : update(entity);
    }

    @Transactional
    public void delete(final long id) {
        if (reposytory.entityExists(id))
            reposytory.delete(id);
        else
            throw new NotFoundException("Registro não encontrado.");
    }
}
