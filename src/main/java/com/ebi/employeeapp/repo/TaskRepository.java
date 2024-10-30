package com.ebi.employeeapp.repo;

import com.ebi.employeeapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.employee = null WHERE t.id = ?1")
    void unassignTask(int id);
}
