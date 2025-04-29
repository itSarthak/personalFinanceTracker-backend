package com.sarthak.expenseTracker.service;

import com.sarthak.expenseTracker.entity.Income;
import com.sarthak.expenseTracker.repository.IncomeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income saveIncome(Income income) {
        try {
            return incomeRepository.save(income);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Income: "+ e.getMessage());
        }
    }

    public List<Income> fetchAllIncome() {
        try {
            return incomeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all Incomes: " + e.getMessage());
        }
    }

    public Optional<Income> fetchIncomeById(Long id) {
        try {
            return incomeRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch income by ID: "+ e.getMessage());
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
            throw new RuntimeException("Failed to update income: "+ e.getMessage());
        }
    }

    public boolean deleteIncome(Long id) {
        try{
            incomeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete income: "+ e.getMessage());
        }
    }

    public List<Income> fetchIncomeByMonth(int year, String month) {
        try {
            int monthNum = Month.valueOf(month.toUpperCase(Locale.ENGLISH)).getValue();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = String.format(year+"-"+monthNum+"-1");
            String endDate = String.format(year+"-"+(monthNum+1)+"-1");
            log.info("Dates have been converted from string to Date datatype");
            List<Income> incomeRecords = incomeRepository.getIncomeRecords_between(startDate, endDate);
            log.info("Got the required response");
            return incomeRecords;
        } catch (Exception e) {
            log.error("There is a error "+ e);
            throw new RuntimeException(e);
        }
    }
}
