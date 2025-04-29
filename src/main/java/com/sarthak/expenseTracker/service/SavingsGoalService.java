package com.sarthak.expenseTracker.service;

import com.sarthak.expenseTracker.entity.SavingsGoal;
import com.sarthak.expenseTracker.repository.SavingsGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsGoalService {
    private SavingsGoalRepository savingsGoalRepository;
    public SavingsGoalService(SavingsGoalRepository savingsRepository) {
        this.savingsGoalRepository = savingsRepository;
    }

    public SavingsGoal saveSavingGoal(SavingsGoal savingsGoal) {
        try {
            return savingsGoalRepository.save(savingsGoal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SavingsGoal> fetchAllsavingsGoal() {
        try {
            return savingsGoalRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all Products: " + e.getMessage());
        }
    }

    public Optional<SavingsGoal> fetchSavingsGoalById(Long id) {
        try {
            return savingsGoalRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed tyo fetch product by ID: "+ e.getMessage());
        }
    }

    public Optional<SavingsGoal> updateSavingsGoal(Long id, SavingsGoal updatedSavingsGoal) {
        try {
            Optional<SavingsGoal> existingsavingsGoalOptional = savingsGoalRepository.findById(id);
            if(existingsavingsGoalOptional.isPresent()) {
                SavingsGoal existingSavingsGoal = existingsavingsGoalOptional.get();
                existingSavingsGoal.setGoalAmount(updatedSavingsGoal.getGoalAmount());
                existingSavingsGoal.setSavedAmount(updatedSavingsGoal.getSavedAmount());
                SavingsGoal savedEntity = savingsGoalRepository.save(existingSavingsGoal);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update product: "+ e.getMessage());
        }
    }

    public boolean deleteSavingsGoal(Long id) {
        try{
            savingsGoalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product: "+ e.getMessage());
        }
    }
}


