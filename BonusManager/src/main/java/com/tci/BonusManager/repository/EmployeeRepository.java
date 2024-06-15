package com.tci.BonusManager.repository;

import com.tci.BonusManager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Retrieves a list of active employees on a given date.
     *
     * @param date The date for which active employees are to be retrieved.
     * @return A list of active employees on the specified date.
     */
    @Query("SELECT e FROM Employee e WHERE e.joiningDate <= :date AND e.exitDate >= :date")
    List<Employee> findActiveEmployeesOnDate(LocalDate date);
}
