package dao;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDAO<A> implements IGenericAccountDAO<A> {

    private final Map<Long, A> database = new HashMap<>();

    @Override
    public void insert(long id, A account) {
        database.put(id, account);
        System.out.println("Database size is " + database.size());
        System.out.println("Your new account's id is " + id + " " + database.containsKey(id));
        System.out.println(getAll());
    }

    @Override
    public void update(long id, A account) {
        database.replace(id, account);
    }

    @Override
    public void delete(long id) {
        database.remove(id);
    }

    @Override
    public A get(long id) {
        return database.get(id);
    }

    @Override
    public Map<Long, A> getAll() {
        return new HashMap<>(database);
    }

    @Override
    public boolean accountExists(long id) {
        if (database.containsKey(id)){ // FIXME: 10/7/2022 Change iban with a long because Strings are not good for hashmap keys
            return true;
        }
        return false;
    }
}
