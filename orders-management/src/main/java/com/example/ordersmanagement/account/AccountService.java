package com.example.ordersmanagement.account;


import java.util.Set;

import org.springframework.stereotype.Service;
@Service
public class AccountService implements AccountServiceInterface {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Boolean signUp(Account account) {
        
        if (accountRepository.containsKey(account.getId())) {
            System.out.println("Account with ID " + account.getId() + " already exists");
            return false;
        }

        for (Account existingAccount : accountRepository.getAccounts()) {
            if (existingAccount.getUsername().equals(account.getUsername())) {
                System.out.println("Username already exists");
                return false;
            }
        }

        for (Account existingAccount : accountRepository.getAccounts()) {
            if (existingAccount.getEmail().equals(account.getEmail())) {
                System.out.println("Email already exists");
                return false;
            }
        }

        for (Account existingAccount : accountRepository.getAccounts()) {
            if (existingAccount.getPhoneNumber().equals(account.getPhoneNumber())) {
                System.out.println("Phone Number already exists");
                return false;
            }
        }

        accountRepository.addAccount(account.getId(), account);

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

        if (account.getPhoneNumber() == null)
        {
            System.out.println("Phone Number field is Empty");
            return false;
        }

        return true;
    }

    public Account login(String username, String password) {
        for (Account account : accountRepository.getAccounts()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    public Account[] getAllAccounts() {
        try {
            Set<Integer> ids = accountRepository.getAccountIds();
            Account[] a = new Account[ids.size()];
            int i=0;
            for(Integer id : ids){
                a[i] = accountRepository.getAccount(id);
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
            return accountRepository.getAccount(id);
        } catch (Exception e) {
            System.out.println("Exception in get account as" + e.getMessage());
        }
        return null;
    }
}