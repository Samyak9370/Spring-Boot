package org.coding.em_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="EmployeeEntity")
public class EmployeeEntity {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private  long id;
  private String name;
  private String phone;
  private String email;
  
}
 