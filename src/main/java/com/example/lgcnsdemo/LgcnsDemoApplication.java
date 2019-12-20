package com.example.lgcnsdemo;

import com.example.lgcnsdemo.command.CreateAccountCommand;
import com.example.lgcnsdemo.command.DecreaseAccountCommand;
import com.example.lgcnsdemo.command.IncreaseAccountCommand;
import com.example.lgcnsdemo.entity.Account;
import com.example.lgcnsdemo.repository.AccountRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SpringBootApplication
public class LgcnsDemoApplication {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(LgcnsDemoApplication.class, args);
	}

	@RequestMapping(value = "create")
	public String create() {
		String accountId = UUID.randomUUID().toString();
		this.commandGateway.send(new CreateAccountCommand(accountId, "genius", 0));
		return accountId;
	}

	@RequestMapping(value = "increase")
	public String increase(
			@RequestParam(value = "accountId") String accountId,
			@RequestParam(value = "balance") Integer balance
	) {
		this.commandGateway.send(new IncreaseAccountCommand(accountId, "genius", 100));
		return "SUCCESS";
	}

	@RequestMapping(value = "decrease")
	public String decrease(
			@RequestParam(value = "accountId") String accountId,
			@RequestParam(value = "balance") Integer balance
	) {
		this.commandGateway.send(new DecreaseAccountCommand(accountId, "genius", 50));
		return "SUCCESS";
	}

	@RequestMapping(value = "account")
	public Account account(
			@RequestParam(value = "accountId") String accountId
	) {
		return this.accountRepository.findById(accountId).orElse(new Account());
	}

}
