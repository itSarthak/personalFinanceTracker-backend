package com.sarthak.expenseTracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


// An entity class is used to map to a database table
@Entity
@Data
@Table(name="income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column(nullable = false)
    private String category;

}
