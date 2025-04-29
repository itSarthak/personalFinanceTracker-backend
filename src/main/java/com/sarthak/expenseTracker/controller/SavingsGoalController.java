package com.sarthak.expenseTracker.controller;

import com.sarthak.expenseTracker.entity.Income;
import com.sarthak.expenseTracker.entity.SavingsGoal;
import com.sarthak.expenseTracker.service.SavingsGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class SavingsGoalController {
    private SavingsGoalService savingsGoalService;
    public SavingsGoalController(SavingsGoalService savingsGoalService) {
        this.savingsGoalService = savingsGoalService;
    }

    @PostMapping("/savings")
    public ResponseEntity<SavingsGoal> addSavingsGoal(@RequestBody SavingsGoal savingsGoal) {
        SavingsGoal savedSavingsGoal = savingsGoalService.saveSavingGoal(savingsGoal);
        return ResponseEntity.ok(savedSavingsGoal);
    }

    @GetMapping("/savings")
    public ResponseEntity<List<SavingsGoal>> getAllSavingsGoal() {
        List<SavingsGoal> savingsGoals = savingsGoalService.fetchAllSavingsGoal();
        return ResponseEntity.ok(savingsGoals);
    }

    @GetMapping("/savings/{id}")
    public ResponseEntity<SavingsGoal> getSavingsGoalById(@PathVariable Long id) {
        Optional<SavingsGoal> savingsGoalOptional = savingsGoalService.fetchSavingsGoalById(id);
        return savingsGoalOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/savings/{id}")
    public ResponseEntity<SavingsGoal> updateSavingsGoal(@PathVariable Long id, @RequestBody SavingsGoal savingsGoal) {
        Optional<SavingsGoal> updateSavingsGoalOptional = savingsGoalService.updateSavingsGoal(id, savingsGoal);
        return updateSavingsGoalOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/savings/{id}")
    public ResponseEntity<String> deleteSavingsGoal(@PathVariable Long id) {
        boolean deletionStatus = savingsGoalService.deleteSavingsGoal(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Savings Goal with ID " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Goal with ID " + id);
        }
    }
}
