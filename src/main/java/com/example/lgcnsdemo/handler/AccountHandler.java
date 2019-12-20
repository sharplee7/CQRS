package com.example.lgcnsdemo.handler;

import com.example.lgcnsdemo.entity.Account;
import com.example.lgcnsdemo.event.CreateAccountEvent;
import com.example.lgcnsdemo.event.DecreaseAccountEvent;
import com.example.lgcnsdemo.event.IncreaseAccountEvent;
import com.example.lgcnsdemo.repository.AccountRepository;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountHandler {

    @Autowired
    private AccountRepository accountRepository;

    @EventSourcingHandler
    public void on(CreateAccountEvent event) {
        Account account = new Account(event.getAccountId(), event.getName(), event.getBalance());
        this.accountRepository.save(account);
    }

    @EventSourcingHandler
    public void on(IncreaseAccountEvent event) {
        Account account = this.accountRepository.findById(event.getAccountId()).orElse(new Account());
        account.setBalance(account.getBalance() + event.getBalance());
        this.accountRepository.save(account);
    }

    @EventSourcingHandler
    public void on(DecreaseAccountEvent event) {
        Account account = this.accountRepository.findById(event.getAccountId()).orElse(new Account());
        account.setBalance(account.getBalance() - event.getBalance());
        this.accountRepository.save(account);
    }
}
