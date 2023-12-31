package com.example.ordersmanagement.account;

public interface AccountServiceInterface {

    public Boolean signUp(Account account);

    public Account login(String username, String password);

    public Account[] getAllAccounts();

    public Account getAccount(int id);
}
