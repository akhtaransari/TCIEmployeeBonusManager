package com.tci.BonusManager;

import com.tci.BonusManager.exception.TCIBonusManagerException;
import com.tci.BonusManager.model.Employee;
import com.tci.BonusManager.repository.EmployeeRepository;
import com.tci.BonusManager.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = BonusManagerApplication.class)
public class BonusManagerApplicationTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {
        // Initialize mock objects
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveEmployees_WithValidData() {
        // Prepare test data
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Akhtar Ansari", "IT", 5000.0, "USD", LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1)));

        // Mock behavior of employeeRepository.saveAll method
        when(employeeRepository.saveAll(employees)).thenReturn(employees);

        // Perform the test
        String result = employeeService.saveEmployees(employees);

        // Verify the result and method invocations
        assertEquals("Successful", result);
        verify(employeeRepository, times(1)).saveAll(employees);
    }

    @Test
    public void testSaveEmployees_WithInvalidData() {
        // Prepare test data with invalid employee
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        // Verify that an exception is thrown when saving invalid data
        assertThrows(TCIBonusManagerException.class, () -> employeeService.saveEmployees(employees));

        // Verify that employeeRepository.saveAll method is never called
        verify(employeeRepository, never()).saveAll(employees);
    }

    @Test
    public void testGetEmployeesEligibleForBonus() {
        // Prepare test data
        LocalDate date = LocalDate.of(2022, 5, 27);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Afsar Ansari", "IT", 5000.0, "USD", LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1)));

        // Mock behavior of employeeRepository.findActiveEmployeesOnDate method
        when(employeeRepository.findActiveEmployeesOnDate(date)).thenReturn(employees);

        // Perform the test
        assertNotNull(employeeService.getEmployeesEligibleForBonus(date));

        // Verify that employeeRepository.findActiveEmployeesOnDate method is called once with the correct date parameter
        verify(employeeRepository, times(1)).findActiveEmployeesOnDate(date);
    }
}
