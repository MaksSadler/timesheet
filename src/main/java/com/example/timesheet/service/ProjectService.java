package com.example.timesheet.service;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.EmployeeRepository;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          EmployeeRepository employeeRepository,
                          TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
    }

    private void findProjectOrThrow(Long projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project with id " + projectId + " does not exist"));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setProjectName(projectDetails.getProjectName());
            return projectRepository.save(project);
        } else {
            return null;
        }
    }

    public void deleteProject(Long id) {
        findProjectOrThrow(id);
        projectRepository.deleteById(id);
    }

    public List<Employee> findProjectEmployees(Long id) {
        findProjectOrThrow(id);
        return projectRepository.findProjectEmployees(id);
    }

    public List<Timesheet> findProjectTimesheets(Long id) {
        if(projectRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Project with id " + id + " does not exist");
        }
        return timesheetRepository.findAllByTimesheetProjectId(id);
    }

}
