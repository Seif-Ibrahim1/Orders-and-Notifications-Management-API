package com.example.ordersmanagement.account;

import com.example.ordersmanagement.account.Account;
import com.example.ordersmanagement.db.InMemoryDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private final InMemoryDB inMemoryDB;

    public AccountRepository(InMemoryDB inMemoryDB) {
        this.inMemoryDB = inMemoryDB;
    }

    public ArrayList<Account> getAccounts() {
        return inMemoryDB.getAccounts();
    }

    public void addAccount(int id, Account account) {
        inMemoryDB.addAccount(id, account);
    }

    public Boolean containsKey(int id) {
        return inMemoryDB.containsKey(id);
    }

    public Account getAccount(int id) {
        return inMemoryDB.getAccount(id);
    }

    public Set<Integer> getAccountIds() {
        return inMemoryDB.getAccountIds();
    }

}
