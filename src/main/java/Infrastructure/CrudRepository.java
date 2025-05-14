package Infrastructure;

import java.util.List;

public interface CrudRepository<T> {

T save(T entity);
List<T> findAll();
void update(T entity);
void delete(int id);
T findById(int id);
}
