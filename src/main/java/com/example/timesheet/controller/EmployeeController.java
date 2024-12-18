package com.example.timesheet.controller;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller",
        description = "Контроллер для работы с сотрудниками.")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Получение всех сотрудников",
            description = "Получение всех сотрудников из БД.")
    @API.Found
    @API.ServerError
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение сотрудника по его ID",
            description = "Получение сотрудника по его ID из БД.")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение списка проектов по ID сотрудника",
            description = "Получение списка проектов по ID сотрудника из БД.")
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getEmployeeProjects(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeProjects(id));
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение списка таймшитов по ID сотрудника",
            description = "Получение списка таймшитов по ID сотрудника из БД.")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getEmployeeTimesheets(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeTimesheets(id));
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение сотрудника по имени",
            description = "Получение сотрудника по имени из БД.")
    @GetMapping("/name/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
        return employeeService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @API.Found
    @API.ServerError
    @Operation(summary = "Создание сотрудника",
            description = "Создание сотрудника в БД.")
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Удаление сотрудника по ID",
            description = "Удаление сотрудника по ID из БД.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
