package com.ebi.employeeapp.service;

import com.ebi.employeeapp.entity.Employee;
import com.ebi.employeeapp.entity.Task;
import com.ebi.employeeapp.model.TaskDTO;
import com.ebi.employeeapp.model.TaskSaveDTO;
import com.ebi.employeeapp.repo.EmployeeRepository;
import com.ebi.employeeapp.repo.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInt {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDTO> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(task -> modelMapper.map(task, TaskDTO.class)).orElse(null);
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskSaveDTO updateTask(TaskSaveDTO taskSaveDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(taskSaveDTO.getId_employee());
        Optional<Task> taskOptional = taskRepository.findById(taskSaveDTO.getId());
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(taskSaveDTO.getTitle());
            task.setDescription(taskSaveDTO.getDescription());
            task.setLocation(taskSaveDTO.getLocation());
            if (employeeOptional.isPresent()) {
                task.setEmployee(employeeOptional.get());
            }
            task = taskRepository.save(task);
            return modelMapper.map(task, TaskSaveDTO.class);
        }
        return null;
    }

    @Override
    public TaskSaveDTO patchTask(TaskSaveDTO taskSaveDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(taskSaveDTO.getId_employee());
        Optional<Task> taskOptional = taskRepository.findById(taskSaveDTO.getId());
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            if (taskSaveDTO.getTitle() != null) {
                existingTask.setTitle(taskSaveDTO.getTitle());
            }
            if (taskSaveDTO.getDescription() != null) {
                existingTask.setDescription(taskSaveDTO.getDescription());
            }
            if (taskSaveDTO.getLocation() != null) {
                existingTask.setLocation(taskSaveDTO.getLocation());
            }
            if (employeeOptional.isPresent()) {
                existingTask.setEmployee(employeeOptional.get());
            }
            Task task = taskRepository.save(existingTask);
            return modelMapper.map(task, TaskSaveDTO.class);
        }
        return null;
    }

    @Override
    public boolean deleteTask(int id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
