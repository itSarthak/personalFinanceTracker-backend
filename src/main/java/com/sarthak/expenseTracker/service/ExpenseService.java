package com.sarthak.expenseTracker.service;

import com.sarthak.expenseTracker.entity.Expense;
import com.sarthak.expenseTracker.repository.ExpenseRepository;
import com.sarthak.expenseTracker.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense saveExpense(Expense expense) {
        try {
            return expenseRepository.save(expense);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Expense: "+ e.getMessage());
        }
    }

    public List<Expense> fetchAllExpense() {
        try {
            return expenseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all Expenses: " + e.getMessage());
        }
    }

    public Optional<Expense> findExpenseById(Long id) {
        try {
            return expenseRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to fetch Expense by ID: "+ e.getMessage());
        }
    }

    public Optional<Expense> updateExpense(Long id, Expense updatedExpense) {
        try {
            Optional<Expense> existingExpenseOptional = expenseRepository.findById(id);
            if (existingExpenseOptional.isPresent()) {
                Expense existingExpense = existingExpenseOptional.get();
                existingExpense.setAmount(updatedExpense.getAmount());
                existingExpense.setType(updatedExpense.getType());
                existingExpense.setDate(updatedExpense.getDate());
                Expense savedEntity = expenseRepository.save(existingExpense);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to update Expense: " + e.getMessage());
        }
    }

    public boolean deleteExpense(Long id) {
        try {
            expenseRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
