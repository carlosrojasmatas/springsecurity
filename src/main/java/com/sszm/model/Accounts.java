package com.sszm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Accounts {

    @Id
    @Column(name = "account_number")
    private long accountNumber;
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;
    @Column(name = "created_at")
    private String createdAt;
}
