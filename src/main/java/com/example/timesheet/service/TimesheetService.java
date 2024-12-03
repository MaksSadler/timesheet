package com.example.timesheet.service;

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
public class TimesheetService {

    private final TimesheetRepository repository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> findById(Long id) {
        return repository.getById(id);
    }

    public List<Timesheet> getAll() {
        return getAll(null,null);
    }

    public List<Timesheet> getAll(LocalDate createBefore, LocalDate createAfter) {
        return repository.getAll(createBefore,createAfter);
    }

    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getProjectId())) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if(projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getId() + " does not exist");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return repository.create(timesheet);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
