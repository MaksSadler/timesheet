package com.example.timesheet.controller;

import com.example.timesheet.model.EmployeeProject;
import com.example.timesheet.service.EmployeeProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/employee-projects")
@Tag(name = "EmployeeProjectController",
        description = "API для управления проектами сотрудников.")
public class EmployeeProjectController {

    private final EmployeeProjectService employeeProjectService;

    public EmployeeProjectController(EmployeeProjectService employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @PostMapping
    public ResponseEntity<EmployeeProject> addEmployeeToProject(@RequestBody Map<String, Long> request) {
        Long employeeId = request.get("employeeId");
        Long projectId = request.get("projectId");

        if (employeeId == null || projectId == null) {
            return ResponseEntity.badRequest().build();
        }
        EmployeeProject createdEmployeeProject =
                employeeProjectService.addEmployeeToProject(employeeId, projectId);
        return ResponseEntity.ok(createdEmployeeProject);
    }
}
