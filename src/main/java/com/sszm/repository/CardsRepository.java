package com.sszm.repository;

import com.sszm.model.Cards;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CardsRepository extends CrudRepository<Cards, Long> {
    List<Cards> findByCustomerId(UUID customerId);
}
