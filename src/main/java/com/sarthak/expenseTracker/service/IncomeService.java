package com.sarthak.expenseTracker.service;

import com.sarthak.expenseTracker.entity.Income;
import com.sarthak.expenseTracker.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income saveIncome(Income income) {
        try {
            return incomeRepository.save(income);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save product: "+ e.getMessage());
        }
    }

    public List<Income> fetchAllIncome() {
        try {
            return incomeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all Products: " + e.getMessage());
        }
    }

    public Optional<Income> fetchIncomeById(Long id) {
        try {
            return incomeRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed tyo fetch product by ID: "+ e.getMessage());
        }
    }

    public Optional<Income> updateIncome(Long id, Income updatedIncome) {
        try {
            Optional<Income> existingIncomeOptional = incomeRepository.findById(id);
            if(existingIncomeOptional.isPresent()) {
                Income existingIncome = existingIncomeOptional.get();
                existingIncome.setType(updatedIncome.getType());
                existingIncome.setAmount(updatedIncome.getAmount());
                existingIncome.setDate(updatedIncome.getDate());
                Income savedEntity = incomeRepository.save(existingIncome);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update product: "+ e.getMessage());
        }
    }

    public boolean deleteIncome(Long id) {
        try{
            incomeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product: "+ e.getMessage());
        }
    }
}
