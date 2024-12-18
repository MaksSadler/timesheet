package com.example.timesheet.controller;

import com.example.timesheet.model.Timesheet;
import com.example.timesheet.service.TimesheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
@Tag(name = "TimesheetController",
        description = "Контроллер для работы с таймшитами.")
public class TimesheetController {

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    // /timesheets/{id}
    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Получение таймшита по ID",
            description = "Получение таймшита по его ID из БД")
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.findById(id);

        return ts.map(timesheet -> ResponseEntity.status(HttpStatus.OK)
                        .body(timesheet)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @API.Found
    @API.ServerError
    @Operation(summary = "Получение всех таймшитов",
            description = "Получение всех таймшитов из БД")
    @GetMapping //получить все
    public ResponseEntity<List<Timesheet>> getAll(
            @RequestParam(required = false) LocalDate createdAtBefore,
            @RequestParam(required = false) LocalDate createdAtAfter) {
        return ResponseEntity.ok(service.findAll(createdAtBefore, createdAtAfter));
    }

    @API.Found
    @API.ServerError
    @Operation(summary = "Создание таймшита",
            description = "Создание таймшита и добавление его в БД")
    @PostMapping //создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        final Timesheet created;
//        try {
        created = service.create(timesheet);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().build();
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @API.Found
    @API.NotFound
    @API.ServerError
    @Operation(summary = "Удаление таймшита по ID",
            description = "Удаление таймшита по ID и обновление БД")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
