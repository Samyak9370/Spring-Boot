package org.coding.em_project.repository;

import org.coding.em_project.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Modifying
    @Transactional  // Important to allow DB change
    @Query(value = "ALTER TABLE EmployeeEntity AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();



    
}
