package dao;

import java.util.Map;

public interface IGenericAccountDAO<A> {
    void insert(long id, A account);
    void update(long id, A account);
    void delete(long id);
    A get(long id);
    Map<Long, A> getAll();
    boolean accountExists(long id);
}
