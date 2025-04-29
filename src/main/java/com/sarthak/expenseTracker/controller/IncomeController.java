package com.sarthak.expenseTracker.controller;

import com.sarthak.expenseTracker.entity.Income;
import com.sarthak.expenseTracker.service.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/income")
    public ResponseEntity<Income> saveIncome(@RequestBody Income income) {
        Income savedIncome  = incomeService.saveIncome(income);
        return ResponseEntity.ok(savedIncome);
    }

    @GetMapping("/income")
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> incomes = incomeService.fetchAllIncome();
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/income/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        log.info("Program is thinking it is getIncomeById method");
        Optional<Income> incomeOptional = incomeService.fetchIncomeById(id);
        return incomeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/income/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income income) {
        Optional<Income> updatedIncomeOptional = incomeService.updateIncome(id, income);
        return updatedIncomeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/income/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
        boolean deletionStatus = incomeService.deleteIncome(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Income with ID" + id + "has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Income with ID" + id);
        }
    }

    @GetMapping("/income/{year}/{month}")
    public ResponseEntity<List<Income>> getIncomeByMonth(@PathVariable int year, @PathVariable String month) {
        log.info("Got the Get mapping");
        List<Income> totalIncomeInMonth = incomeService.fetchIncomeByMonth(year, month);
        return ResponseEntity.ok(totalIncomeInMonth);
    }

}
