package com.sszm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity

public class Cards {
    @Id
    @Column(name = "card_id")
    private Integer cardId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "total_limit")
    private Integer limit;
    @Column(name = "amount_used")
    private Integer amountUsed;
    @Column(name = "available_amount")
    private Integer availableAmount;
    @Column(name = "create_dt")
    private LocalDate createdDt;


    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Integer amountUsed) {
        this.amountUsed = amountUsed;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

}
