package com.example.lgcnsdemo.aggregate;

import com.example.lgcnsdemo.command.CreateAccountCommand;
import com.example.lgcnsdemo.command.DecreaseAccountCommand;
import com.example.lgcnsdemo.command.IncreaseAccountCommand;
import com.example.lgcnsdemo.event.CreateAccountEvent;
import com.example.lgcnsdemo.event.DecreaseAccountEvent;
import com.example.lgcnsdemo.event.IncreaseAccountEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;

    private String name;

    private int balance;

    public AccountAggregate() {}

    /**
     * Receive a Create account command and push a create account event
     */
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(new CreateAccountEvent(command.getAccountId(), command.getName(), command.getBalance()));
    }

    /**
     * Receive a create account event and init account
     */
    @EventSourcingHandler
    public void on(CreateAccountEvent event) {
        this.accountId = event.getAccountId();
        this.name = event.getName();
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void handle(IncreaseAccountCommand command) {
        AggregateLifecycle.apply(new IncreaseAccountEvent(command.getAccountId(), command.getName(), command.getBalance()));
    }

    @EventSourcingHandler
    public void on(IncreaseAccountEvent event) {
        this.balance = this.balance + event.getBalance();
    }

    @CommandHandler
    public void handle(DecreaseAccountCommand command) {
        AggregateLifecycle.apply(new DecreaseAccountEvent(command.getAccountId(), command.getName(), command.getBalance()));
    }

    @EventSourcingHandler
    public void on(DecreaseAccountEvent event) {
        this.balance = this.balance - event.getBalance();
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
