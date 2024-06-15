package com.tci.BonusManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empName;
    private String department;
    private Double amount;
    private String currency;
    private LocalDate joiningDate;
    private LocalDate exitDate;

    public Employee(String empName, String department, Double amount, String currency, LocalDate joiningDate, LocalDate exitDate) {

        this.empName = empName;
        this.department = department;
        this.amount = amount;
        this.currency = currency;
        this.joiningDate = joiningDate;
        this.exitDate = exitDate;
    }
}
