package com.example.lgcnsdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    private String accountId;

    private String name;

    private int balance;

    public Account(){}

    public Account(String accountId, String name, int balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
