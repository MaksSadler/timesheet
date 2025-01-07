package com.example.timesheet.repository;

import com.example.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    // select * from timesheet where project_id = $1
    //List<Timesheet> findByProjectId(Long projectId);

    // select * from timesheets where project_id = $1 and minutes = $2
   // List<Timesheet> findByProjectIdAndMinutes (Long projectId, Integer minutes);

    // select * from timesheets where created_at > $1
   // List<Timesheet> findByCreatedAtGreaterThan(LocalDate createdAt);

    // select * from timesheets where created_at > $1 and < $2
    List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max);

    // select * from timesheets where project_id is null
   // List<Timesheet> findByProjectIdIsNull();

    //jql - java query language
    //Query("select t from Timesheet t where t.timesheetProjectId = :projectId order by t.createdAt desc")
    //List<Timesheet> findByProjectId(Long projectId);

    List<Timesheet> findAllByTimesheetEmployeeId(Long employeeId);

    List<Timesheet> findAllByTimesheetProjectId(Long employeeId);

}
