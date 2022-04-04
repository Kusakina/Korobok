package dashakys.korob.ok.service;

import dashakys.korob.ok.model.DatabaseEntity;

import java.util.List;

public interface EntityService<T extends DatabaseEntity> {

    List<T> findAll();

    T save(T entity);
    void remove(T entity);
}
