package com.example.timesheet.repository;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeName(String employeeName);

    @Query("SELECT p FROM EmployeeProject ep JOIN ep.project p WHERE ep.employee.employeeId = :id")
    List<Project> findEmployeeProjects(@Param("id")Long id);

    Optional<Employee> findByEmployeeId(Long id);

    @Query("SELECT tm FROM Timesheet tm WHERE tm.timesheetEmployeeId = :id")
    List<Timesheet> findEmployeeTimesheets(@Param("id")Long id);

}
