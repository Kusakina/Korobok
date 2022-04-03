package service;

import model.DatabaseEntity;

import java.util.List;

public interface EntityService<T extends DatabaseEntity> {

    List<T> findAll();

    T save(T entity);
    void remove(T entity);
}
