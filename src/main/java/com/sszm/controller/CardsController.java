package com.sszm.controller;

import com.sszm.model.Cards;
import com.sszm.repository.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/cards")
public class CardsController {

    private final CardsRepository cardsRepository;

    public CardsController(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }


    @GetMapping("myCards")
    public List<Cards> getCards(@RequestParam UUID customerId){
        return cardsRepository.findByCustomerId(customerId);
    }

}
