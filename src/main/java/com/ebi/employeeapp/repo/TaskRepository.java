package com.ebi.employeeapp.repo;

import com.ebi.employeeapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
