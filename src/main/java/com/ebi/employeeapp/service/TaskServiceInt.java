package com.ebi.employeeapp.service;

import com.ebi.employeeapp.model.TaskDTO;
import com.ebi.employeeapp.model.TaskSaveDTO;

import java.util.List;

public interface TaskServiceInt {
    public List<TaskDTO> getTasks();
    public TaskDTO getTaskById(int id);
    public TaskDTO addTask(TaskDTO taskDTO);
    public TaskSaveDTO updateTask(TaskSaveDTO taskSaveDTO);
    public TaskSaveDTO patchTask(TaskSaveDTO taskSaveDTO);
    public boolean deleteTask(int id);
}
