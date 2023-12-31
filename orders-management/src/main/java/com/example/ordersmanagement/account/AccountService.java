package com.example.ordersmanagement.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
@Service
public class AccountService implements AccountServiceInterface {

    public Boolean signUp(Account account) {

        if (AccountRepositry.accounts.containsKey(account.getId())) {
            System.out.println("Account with ID " + account.getId() + " already exists");
            return false;
        }

        for (Account existingAccount : AccountRepositry.accounts.values()) {
            if (existingAccount.getUsername().equals(account.getUsername())) {
                System.out.println("Username already exists");
                return false;
            }
        }

        for (Account existingAccount : AccountRepositry.accounts.values()) {
            if (existingAccount.getEmail().equals(account.getEmail())) {
                System.out.println("Email already exists");
                return false;
            }
        }

        AccountRepositry.accounts.put(account.getId(), account);

        if (account.getAddress().getStreet() == null)
        {
            System.out.println("Street field is Empty");
            return false;
        }

        if (account.getAddress().getCity() == null)
        {
            System.out.println("City field is Empty");
            return false;
        }

        if (account.getEmail() == null)
        {
            System.out.println("Email field is Empty");
            return false;
        }

        return true;
    }

    public Account login(String username, String password) {
        for (Account account : AccountRepositry.accounts.values()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    public Account[] getAllAccounts() {
        try {
            Set<Integer> ids = AccountRepositry.accounts.keySet();
            Account[] a = new Account[ids.size()];
            int i=0;
            for(Integer id : ids){
                a[i] = AccountRepositry.accounts.get(id);
                i++;
            }
            return a;
        } catch (Exception e) {
            System.out.println("Exception in getAllAccounts as" + e.getMessage());
        }
        return null;
    }

    public Account getAccount(int id) {
        try {
            return AccountRepositry.accounts.get(id);
        } catch (Exception e) {
            System.out.println("Exception in get account as" + e.getMessage());
        }
        return null;
    }
}