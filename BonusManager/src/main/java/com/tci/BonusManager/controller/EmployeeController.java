package com.tci.BonusManager.controller;

import com.tci.BonusManager.dto.BonusInfoDTO;
import com.tci.BonusManager.model.Employee;
import com.tci.BonusManager.service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tci")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInterface employeeServiceInterface;

    /**
     * POST endpoint to save a list of employees.
     *
     * @param employees the list of employees to be saved
     * @return a ResponseEntity with the status ACCEPTED if successful
     */
    @PostMapping("/employee-bonus")
    public ResponseEntity<String> saveEmployees(@RequestBody List<Employee> employees) {
        return new ResponseEntity<>(employeeServiceInterface.saveEmployees(employees), HttpStatus.ACCEPTED);
    }

    /**
     * GET endpoint to retrieve employees eligible for bonus on a given date.
     *
     * @param date the date for which eligible employees are requested
     * @return a ResponseEntity with the eligible employees and their bonus info DTOs with status OK
     */
    @GetMapping("/employee-bonus")
    public ResponseEntity<Map<String, List<BonusInfoDTO>>> getEmployeesEligibleForBonus(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(employeeServiceInterface.getEmployeesEligibleForBonus(date), HttpStatus.OK);
    }
}
