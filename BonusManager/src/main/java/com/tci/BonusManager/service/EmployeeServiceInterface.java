package com.tci.BonusManager.service;

import com.tci.BonusManager.dto.BonusInfoDTO;
import com.tci.BonusManager.model.Employee;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {

    String saveEmployees(List<Employee> employees) ;
    Map<String, List<BonusInfoDTO>> getEmployeesEligibleForBonus(LocalDate date);
}
