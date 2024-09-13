package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.TaskDTO;
import com.ebi.employeeapp.model.TaskSaveDTO;

import java.util.List;

public interface TaskServiceInt {
    List<TaskDTO> getTasks();
    TaskDTO getTaskById(int id);
    TaskDTO addTask(TaskDTO taskDTO);
    TaskSaveDTO updateTask(TaskSaveDTO taskSaveDTO);
    TaskSaveDTO patchTask(TaskSaveDTO taskSaveDTO);
    boolean deleteTask(int id);
}
