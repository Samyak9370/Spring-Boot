package org.coding.em_project.services;

import java.util.List;

import org.coding.em_project.model.Employee;
public interface EmployeeService {
   String createEmployee(Employee employee);
   List<Employee>  readEmployees();
   boolean deleteEmployee(Long id);
   String updateEmployee(Long id,Employee employee);
   Employee readEmployee(Long id);

}
