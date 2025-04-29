package com.sarthak.expenseTracker.repository;

import com.sarthak.expenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// This is a Repository interface, it provides a set of methods

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query(nativeQuery = true, value = "select * from income where income.date between :startDate and :endDate")
    List<Income> getIncomeRecords_between(@Param("startDate") String date1, @Param("endDate") String date2);
}
