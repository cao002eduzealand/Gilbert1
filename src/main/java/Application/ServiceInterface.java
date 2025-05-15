package Application;

import java.util.List;


public interface ServiceInterface<T> {

    T save(T entity);

    List<T> findAll();

    void update(T entity);
    void delete(int id);
    T findById(int id);

}

