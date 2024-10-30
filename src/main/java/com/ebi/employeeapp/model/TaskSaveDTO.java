package com.ebi.employeeapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSaveDTO {
    private int id;
    private String title;
    private String description;
    private String location;
    private Integer id_employee;
}
