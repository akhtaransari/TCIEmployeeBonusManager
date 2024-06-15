package com.tci.BonusManager.service;

import com.tci.BonusManager.dto.BonusInfoDTO;
import com.tci.BonusManager.exception.TCIBonusManagerException;
import com.tci.BonusManager.model.Employee;
import com.tci.BonusManager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Saves a list of employees after performing validation checks.
     *
     * @param employees The list of employees to be saved.
     * @return A string indicating the success of the operation.
     * @throws TCIBonusManagerException If any employee data is invalid.
     */
    @Override
    public String saveEmployees(List<Employee> employees) {
        // Filter out invalid employees
        List<Employee> invalidEmployees = employees.stream()
                .filter(this::isInvalidEmployee)
                .toList();

        // Throw exception if any invalid employees are found
        if (!invalidEmployees.isEmpty()) {
            throw new TCIBonusManagerException("Invalid employee data: " + invalidEmployees);
        }

        // Save the valid employees
        employeeRepository.saveAll(employees);
        return "Successful";
    }

    /**
     * Retrieves eligible employees for bonus on a given date.
     *
     * @param date The date for which to check eligibility.
     * @return A map containing bonus information grouped by currency.
     */
    @Override
    public Map<String, List<BonusInfoDTO>> getEmployeesEligibleForBonus(LocalDate date) {
        // Retrieve active employees on the given date
        List<Employee> activeEmployees = employeeRepository.findActiveEmployeesOnDate(date);

        // Throw exception if no active employees are found
        if (activeEmployees.isEmpty()) {
            throw new TCIBonusManagerException("No active employees found for the given date: " + date);
        }

        // Group employees by currency and calculate bonus info
        Map<String, List<BonusInfoDTO>> bonusInfoMap = new HashMap<>();
        for (Employee employee : activeEmployees) {
            String currency = employee.getCurrency();
            double amount = employee.getAmount();

            // Create bonus info object
            BonusInfoDTO bonusInfo = new BonusInfoDTO(employee.getEmpName(), amount);

            // Add bonus info to the map, grouping by currency
            bonusInfoMap.computeIfAbsent(currency, k -> new ArrayList<>()).add(bonusInfo);
        }

        return bonusInfoMap;
    }


    /**
     * Checks if the given employee data is valid.
     *
     * @param employee The employee to validate.
     * @return True if the employee data is invalid, otherwise false.
     */
    private boolean isInvalidEmployee(Employee employee) {
        // Check if any mandatory fields are null or empty
        if (employee.getEmpName() == null || employee.getEmpName().isEmpty()
                || employee.getDepartment() == null || employee.getDepartment().isEmpty()
                || employee.getAmount() == null
                || employee.getCurrency() == null || employee.getCurrency().isEmpty()) {
            return true;
        }

        // Check if joiningDate and exitDate are not null and in the past
        LocalDate currentDate = LocalDate.now();
        LocalDate joiningDate = employee.getJoiningDate();
        LocalDate exitDate = employee.getExitDate();

        return joiningDate == null || exitDate == null || joiningDate.isAfter(currentDate) || exitDate.isAfter(currentDate);
    }
}
