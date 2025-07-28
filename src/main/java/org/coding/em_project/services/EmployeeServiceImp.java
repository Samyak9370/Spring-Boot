
package org.coding.em_project.services;

import java.util.ArrayList;
import java.util.List;

import org.coding.em_project.entity.EmployeeEntity;
import org.coding.em_project.model.Employee;
import org.coding.em_project.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImp implements EmployeeService {

@Autowired    
private   EmployeeRepository employeeRepository; 
// List<Employee> employees=new ArrayList<>();



    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity=new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Saved sucessfully ";
         
    }  
    @Override
    public Employee readEmployee(Long id) {
          EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
          Employee employee=new Employee();
          BeanUtils.copyProperties(employeeEntity,employee); 
        return employee; 
       
    }
   
   

    @Override
    public List<Employee> readEmployees() {
     List<EmployeeEntity>employeeList= employeeRepository.findAll();
     List<Employee> employees=new ArrayList<>();
     
     for (EmployeeEntity employeeEntity : employeeList) {
        Employee emp=new Employee();
        emp.setId(employeeEntity.getId());
        emp.setName(employeeEntity.getName());
        emp.setEmail(employeeEntity.getEmail());
        emp.setPhone(employeeEntity.getPhone());

        employees.add(emp);
     }
        return employees; 
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity emp=employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
        return true;

       
    }

    @Override
    public String updateEmployee(Long id,Employee employee) {
            EmployeeEntity exitingEmployee=employeeRepository.findById(id).get(); 
            exitingEmployee.setEmail(employee.getEmail());
            exitingEmployee.setPhone((employee.getPhone()));
            exitingEmployee.setName((employee.getName()));
        employeeRepository.save(exitingEmployee);
        return "Update Sucessfully";
    }

}


   

