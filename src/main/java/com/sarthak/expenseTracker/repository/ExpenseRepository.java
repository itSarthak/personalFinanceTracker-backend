package com.sarthak.expenseTracker.repository;

import com.sarthak.expenseTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(nativeQuery = true, value = "select * from expense where expense.date between :startDate and :endDate")
    List<Expense> getExpenseRecords_between(@Param("startDate") String date1, @Param("endDate") String date2);

    @Query(nativeQuery = true, value = "select sum(expense.amount) from expense where expense.date between :startDate and :endDate")
    Long getTotalExpenseByMonth(@Param("startDate") String date1, @Param("endDate") String date2);

    @Query(nativeQuery = true, value = "select distinct expense.type from expense")
    List<String> getDistinctCategories();
}
