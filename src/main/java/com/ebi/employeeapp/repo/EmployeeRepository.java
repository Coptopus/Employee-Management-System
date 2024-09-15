package com.ebi.employeeapp.repo;

import com.ebi.employeeapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select e from Employee e where e.name = :name")
    Optional<Employee> findByName(String name);

    List<Employee> findByNameContainingIgnoreCase(String name);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%',:name,'%') ) ")
    List<Employee> searchByName(String name);
}
