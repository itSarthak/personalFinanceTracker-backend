package com.sarthak.expenseTracker.controller;
import com.sarthak.expenseTracker.entity.Expense;
import com.sarthak.expenseTracker.repository.ExpenseRepository;
import com.sarthak.expenseTracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
    }

    @PostMapping("/expense")
    public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense) {
        Expense savedExpense  = expenseService.saveExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping("/expense")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.fetchAllExpense();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<Expense> getExpenseBId(@PathVariable Long id) {
        Optional<Expense> expenseOptional = expenseService.findExpenseById(id);
        return expenseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/expense/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Optional<Expense> expenseOptional = expenseService.updateExpense(id, expense);
        return expenseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        boolean deletionStatus = expenseService.deleteExpense(id);
        if(deletionStatus) {
            return ResponseEntity.ok("Expense with ID" + id + "has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete expense with ID" + id);
        }
    }

    @GetMapping("/expense/{year}/{month}")
    public ResponseEntity<List<Expense>> getExpenseByMonth(@PathVariable int year, @PathVariable String month) {
        log.info("Got the Get mapping");
        List<Expense> totalExpenseRecordsInMonth = expenseService.fetchExpenseByMonth(year, month);
        return ResponseEntity.ok(totalExpenseRecordsInMonth);
    }

    @GetMapping("/expense/total/{year}/{month}")
    public ResponseEntity<Long> getTotalExpenseByMonth(@PathVariable int year, @PathVariable String month) {
        Long totalExpenseByMonth = expenseService.fetchTotalExpenseByMonth(year, month);
        return ResponseEntity.ok(totalExpenseByMonth);
    }
}
