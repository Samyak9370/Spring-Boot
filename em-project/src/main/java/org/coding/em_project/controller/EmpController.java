package org.coding.em_project.controller;

import java.util.List;

import org.coding.em_project.model.Employee;
import org.coding.em_project.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmpController { 
    
// List<Employee> employees=new ArrayList<>();
// EmployeeService employeeService=new EmployeeServiceImp();
@Autowired
EmployeeService employeeService;                //Dependency injection

@GetMapping("employees") 
public List<Employee> getAllEmployee() {
    return employeeService.readEmployees();
}

@GetMapping("employees/{id}") 
public Employee getEmployeeBYId(@PathVariable Long id) {
    return employeeService.readEmployee(id);
}

@PostMapping("employees")
public String createEmployees(@RequestBody Employee employee) {
return   employeeService.createEmployee(employee);
  
}

@DeleteMapping("employees/{id}")
public  String deleteEmployee(@PathVariable Long id){
  if(employeeService.deleteEmployee(id))
   return "delete sucessfully";
return"not found";

}
@PutMapping("employees/{id}")
public String postMethodName(@PathVariable Long id,@RequestBody Employee employee) {
    return employeeService.updateEmployee(id, employee); 
}



}