package com.example.timesheet.service;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private void findProjectOrThrow(Long id) {
        employeeRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Project with id " + id + " does not exist"));
    }

    public Optional<Employee> findById(Long id) {
        findProjectOrThrow(id);
        return employeeRepository.findByEmployeeId(id);
    }

    public Optional<Employee> findByName(String name) {
        return Optional.ofNullable(employeeRepository.findByEmployeeFirstName(name)
        .orElseThrow(() -> new NoSuchElementException("Project with name " + name + " does not exist")));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        if(employeeRepository.findByEmployeeFirstName(employee.getEmployeeFirstName()).isPresent()) {
            throw new IllegalArgumentException
                    ("Project with name " + employee.getEmployeeFirstName() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        findProjectOrThrow(id);
        employeeRepository.deleteById(id);
    }

    public List<Project> findEmployeeProjects(Long id) {
        findProjectOrThrow(id);
        return employeeRepository.findEmployeeProjects(id);
    }

    public List<Timesheet> findEmployeeTimesheets(Long id) {
        findProjectOrThrow(id);
        return employeeRepository.findEmployeeTimesheets(id);
    }
}
