package dao;

import model.Account;

import java.util.HashMap;
import java.util.Map;

public class VasiDedomenwn {
    static Map<Long, Account> database = new HashMap<>();

    public static Map<Long, Account> getDatabase() {
        return database;
    }
}
