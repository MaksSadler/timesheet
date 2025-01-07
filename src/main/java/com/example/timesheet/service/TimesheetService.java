package com.example.timesheet.service;

import com.example.timesheet.aspect.Timer;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service // то же самое что и component, просто специально называется service,
//чтобы различать сервисный слой
@Timer
public class TimesheetService {

    private final TimesheetRepository repository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

//    @Timer
    public Optional<Timesheet> findById(Long id) {
        System.out.println("LOG INSIDE METHOD");
        return repository.findById(id);
    }

    public List<Timesheet> findAll() {
        return findAll(null,null);
    }

    public List<Timesheet> findAll(LocalDate createBefore, LocalDate createAfter) {
        return repository.findAll();
    }

    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getTimesheetProjectId())) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if(projectRepository.findById(timesheet.getTimesheetProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getTimesheetId() + " does not exist");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return repository.save(timesheet);
    }

//    @Timer(enabled = false)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
