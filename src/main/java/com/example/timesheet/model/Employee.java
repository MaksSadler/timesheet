package com.example.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_firstName")
    private String employeeFirstName;

    @Column(name = "employee_lastName")
    private String employeeLastName;

}
