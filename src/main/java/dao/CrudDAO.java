package dao;

import java.util.*;

public interface CrudDAO<T, K> {
    T findById(K id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(K id);
}
