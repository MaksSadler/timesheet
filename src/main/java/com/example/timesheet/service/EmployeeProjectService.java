package com.example.timesheet.service;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.EmployeeProject;
import com.example.timesheet.model.Project;
import com.example.timesheet.repository.EmployeeProjectRepository;
import com.example.timesheet.repository.EmployeeRepository;
import com.example.timesheet.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeProjectRepository employeeProjectRepository;

    public EmployeeProjectService(EmployeeRepository employeeRepository,
                                  ProjectRepository projectRepository,
                                  EmployeeProjectRepository employeeProjectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.employeeProjectRepository = employeeProjectRepository;
    }

    public EmployeeProject addEmployeeToProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException
                        ("Employee with id " + employeeId + " not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException
                ("Project with id " + projectId + " not found"));

        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setEmployee(employee);
        employeeProject.setProject(project);
        return employeeProjectRepository.save(employeeProject);
    }
}
