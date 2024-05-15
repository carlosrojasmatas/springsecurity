package com.sszm.repository;

import com.sszm.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AccountTrxRepository extends CrudRepository<AccountTransactions, Long>
{
    List<AccountTransactions> findAccountTransactionsByAccountNumberOrderByTransactionDateDesc(long id);
}
