package com.sszm.repository;

import com.sszm.model.Accounts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    List<Accounts> findAllByCustomerId(UUID customerId);

}
