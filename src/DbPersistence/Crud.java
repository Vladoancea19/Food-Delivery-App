package DbPersistence;

import java.util.List;
import java.util.Optional;

public interface Crud <T, Id> {

    T create(T entity);

    List<T> read();

    Optional<T> update(T entity, Id id);

    void delete(Id id);

    Optional<T> findById(Id id);
}
