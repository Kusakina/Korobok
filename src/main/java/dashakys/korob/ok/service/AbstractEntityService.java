package dashakys.korob.ok.service;

import lombok.RequiredArgsConstructor;
import dashakys.korob.ok.model.DatabaseEntity;
import org.springframework.transaction.annotation.Transactional;
import dashakys.korob.ok.repository.EntityRepository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public abstract class AbstractEntityService<
            T extends DatabaseEntity, RepositoryType extends EntityRepository<T>>implements EntityService<T>  {

    protected final RepositoryType repository;

    @Override
    public List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new EntityServiceException();
        }
    }

    @Override
    public T save(T entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new EntityServiceException();
        }
    }

    @Override
    public void remove(T entity) {
        try {
            repository.delete(entity);
        } catch (Exception e) {
            throw new EntityServiceException();
        }
    }
}

