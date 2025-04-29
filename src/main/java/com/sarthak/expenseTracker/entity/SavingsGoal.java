package com.sarthak.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SavingsGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long goalAmount;

    @Column(nullable = true)
    private Long savedAmount;

    @Column(nullable = false)
    private String title;
}
