package com.sszm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "account_transactions")
@Data
public class AccountTransactions {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_number")
    private Integer accountNumber;
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "transaction_dt")
    private LocalDate transactionDate;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_amount")
    private double transactionAmount;
    @Column(name = "transaction_summary")
    private String summary;
    @Column(name = "closing_balance")
    private double closing_balance;
    @Column(name = "created_at")
    private LocalDate createdAt;
}
