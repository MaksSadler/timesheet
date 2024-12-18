package com.example.timesheet.controller;

import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @API.Found
    @API.ServerError
    @Operation(summary = "Получение всех проектов",
                description = "Получение всех проектов из БД.")
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Project",
            description = "Получить проект по его идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content
                            (schema = @Schema(implementation = Project.class))),
                    @ApiResponse(description = "Проект не найден", responseCode = "404", content = @Content
                            (schema = @Schema(implementation = Void.class))),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content
                            (schema = @Schema(implementation = Void.class)))})
    public ResponseEntity<Project> getProjectById(
            @PathVariable
            @Parameter(description = "Идентификатор проекта") long id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @API.Found
    @API.ServerError
    @Operation(summary = "Создание нового проекта",
            description = "Создание нового проекта в БД.")
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.status(201).body(createdProject);
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Изменение проекта по ID.",
            description = "Изменение проекта по ID в БД.")
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable long id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Удалить проект по его ID.",
            description = "Удалить проект по его ID из БД.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение списка табелей проекта по его ID.",
            description = "Получение списка табелей проекта по его ID из БД.")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable long id) {
        List<Timesheet> timesheet = projectService.findProjectTimesheets(id);
        if (timesheet != null) {
            return ResponseEntity.ok(timesheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
