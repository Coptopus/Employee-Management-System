package com.ebi.employeeapp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeSaveDTO {
    private String name;
    private double salary;
}
