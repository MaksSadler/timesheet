package com.example.timesheet.repository;

import com.example.timesheet.model.Project;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {

    private static Long sequence = 1L;
    private final static String name = "Project";
    private final List<Project> projects = new ArrayList<>();

    public List<Project> findAll() {
        return new ArrayList<>(projects);
    }

    public Optional<Project> findById(Long id) {
        return projects.stream()
                .filter(project -> Objects.equals(project.getId(), id))
                .findFirst();
    }

    public Project save(Project project) {
        if (project.getId() == null) {
            project.setId(sequence++);
            project.setName(name);
        } else {
            deleteById(project.getId());
        }
        projects.add(project);
        return project;
    }

    public void deleteById(Long id) {
        projects.removeIf(project -> Objects.equals(project.getId(), id));
    }
}
