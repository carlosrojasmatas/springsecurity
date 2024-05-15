package com.sszm.controller;

import com.sszm.model.Loans;
import com.sszm.repository.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/loans")
public class LoansController {

    private final LoanRepository loanRepository;

    public LoansController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("myLoans") // Base path for all endpoints
    public List<Loans> getLoans(@RequestParam UUID customerId){
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }

}
