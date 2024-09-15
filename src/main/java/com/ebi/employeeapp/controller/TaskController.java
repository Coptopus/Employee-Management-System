package com.ebi.employeeapp.controller;

import com.ebi.employeeapp.model.TaskDTO;
import com.ebi.employeeapp.model.TaskSaveDTO;
import com.ebi.employeeapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/view")
    public String showTasks(Model model) {
        List<TaskDTO> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        TaskDTO task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) {
        TaskDTO newTask = taskService.addTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<TaskSaveDTO> updateTask(@RequestBody TaskSaveDTO task) {
        TaskSaveDTO updatedTask = taskService.updateTask(task);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping
    @ResponseBody
    public  ResponseEntity<TaskSaveDTO> patchTask(@RequestBody TaskSaveDTO task) {
        TaskSaveDTO updatedTask = taskService.patchTask(task);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
