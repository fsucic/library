package com.example.library.controllers;

import com.example.library.controllers.requests.LoanRequest;
import com.example.library.controllers.requests.UpdateLoanRequest;
import com.example.library.controllers.responses.LoanView;
import com.example.library.models.LoanModel;
import com.example.library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(@Autowired LoanService loanService) {
        this.loanService = loanService;
    }

    @Transactional
    @PostMapping
    public LoanView createLoan(@RequestBody LoanRequest loanRequest) {
        LoanModel loanModel = loanService.createLoan(loanRequest);
        return new LoanView(loanModel);
    }

    @Transactional
    @GetMapping("/{loanId}")
    public LoanView readOne(@PathVariable String loanId) {
        return new LoanView(loanService.readOne(Long.parseLong(loanId)));
    }

    @Transactional
    @GetMapping
    public Set<LoanView> readAll() {
        return loanService.readAll().stream().map(LoanView::new).collect(Collectors.toSet());
    }


    @Transactional
    @DeleteMapping("/{loanId}")
    public long deleteLoan(@PathVariable String loanId) { //what to return?
        return loanService.deleteLoan(Long.parseLong(loanId));
    }

    @Transactional
    @PutMapping
    public LoanView updateLoan(@RequestBody UpdateLoanRequest updateLoanRequest) { //what to return?
        return new LoanView(loanService.updateLoan(updateLoanRequest));
    }

    @Transactional
    @GetMapping("/member/{memberId}")
    public List<LoanView> getMemberLoans(@PathVariable String memberId) {
        return loanService.getMemberLoans(Long.parseLong(memberId)).stream()
                .map(LoanView::new).collect(Collectors.toList());
    }
}