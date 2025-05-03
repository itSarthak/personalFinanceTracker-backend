package com.sarthak.expenseTracker.service;

import com.sarthak.expenseTracker.entity.Expense;
import com.sarthak.expenseTracker.entity.Expense;
import com.sarthak.expenseTracker.repository.ExpenseRepository;
import com.sarthak.expenseTracker.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
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

    public List<Expense> fetchExpenseByMonth(int year, String month) {
        try {
            int monthNum = Month.valueOf(month.toUpperCase(Locale.ENGLISH)).getValue();
            String startDate = String.format(year+"-"+monthNum+"-1");
            String endDate = String.format(year+"-"+(monthNum+1)+"-1");
            log.info("Dates have been converted from string to Date datatype");
            List<Expense> incomeRecords = expenseRepository.getExpenseRecords_between(startDate, endDate);
            log.info("Got the required response");
            return incomeRecords;
        } catch (Exception e) {
            log.error("There is a error "+ e);
            throw new RuntimeException(e);
        }
    }

    public Long fetchTotalExpenseByMonth(int year, String month) {
        try {
            int monthNum = Month.valueOf(month.toUpperCase(Locale.ENGLISH)).getValue();
            String startDate = String.format(year+"-"+monthNum+"-1");
            String endDate = String.format(year+"-"+(monthNum+1)+"-1");
            log.info("Dates have been converted from string to Date datatype");
            Long incomeRecords = expenseRepository.getTotalExpenseByMonth(startDate, endDate);
            log.info("Got the required response");
            return incomeRecords;
        } catch (Exception e) {
            log.error("There is a error "+ e);
            throw new RuntimeException(e);
        }
    }

//    public List<String> getExpenseCategorySummary()

}
