package com.sszm.controller;

import com.sszm.model.AccountTransactions;
import com.sszm.repository.AccountTrxRepository;
import com.sszm.repository.AccountsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/balances")
public class BalanceController {
    private final AccountTrxRepository accountTrxRepository;
    private final AccountsRepository accountsRepository;

    public BalanceController(AccountTrxRepository accountTrxRepository, AccountsRepository accountsRepository) {
        this.accountTrxRepository = accountTrxRepository;
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("myBalance")
    public List<AccountTransactions> myBalance(@RequestParam UUID customerId) {
        var accounts = accountsRepository.findAllByCustomerId(customerId);
        return accounts.stream()
                .flatMap(acc -> accountTrxRepository.
                        findAccountTransactionsByAccountNumberOrderByTransactionDateDesc(acc.getAccountNumber())
                        .stream())
                .collect(Collectors.toList());

    }
}
