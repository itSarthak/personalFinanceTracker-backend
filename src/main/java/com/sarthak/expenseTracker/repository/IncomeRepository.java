package com.sarthak.expenseTracker.repository;

import com.sarthak.expenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is a Repository interface, it provides a set of methods

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
