package com.ebi.employeeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    private int id_employee;
    private String name;
    private double salary;
}