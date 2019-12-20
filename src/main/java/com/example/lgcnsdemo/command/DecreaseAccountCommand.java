package com.example.lgcnsdemo.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DecreaseAccountCommand {

    @TargetAggregateIdentifier
    private String accountId;

    private String name;

    private int balance;

    public DecreaseAccountCommand(String accountId, String name, int balance) {
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
