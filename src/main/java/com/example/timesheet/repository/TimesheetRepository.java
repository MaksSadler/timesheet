package com.example.timesheet.repository;

import com.example.timesheet.model.Timesheet;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository // @Component для классов работающих с данными.
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    //GET - получить (не содержит тела)
    //POST - создать
    //PUT - изменить (изменяет все)
    //PATCH - изменить (изменяет только конкретное объявленное поле)
    //DELETE - удалить

//    @GetMapping("/timesheets")
//    public Timesheet get() {
//        Timesheet timesheet = new Timesheet();
//        timesheet.setId(1L);
//        timesheet.setProject("spring");
//        timesheet.setMinutes(200);
//        timesheet.setCreatedAt(LocalDate.now());
//        return timesheet;
//    }

    //@GetMapping("/timesheets/{id}") получить конкретную запись по идентификатору
    //@DeleteMapping("/timesheets/{id}") удалить конкретную запись по идентификатору
    //@PutMapping("/timesheets/{id}") обновить конкретную запись по идентификатору

    //@GetMapping("/timesheets/{id}") //получить по идентификатору
    public Optional<Timesheet> getById(Long id) {
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    //@GetMapping("/timesheets") //получить все
    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    //@PostMapping("/timesheets") //создание нового ресурса
    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheet.setProjectId(timesheet.getProjectId());
        timesheets.add(timesheet);

        //Location: /timesheets/sequence
        //201 status Created
        return timesheet;
    }

    //@DeleteMapping("/timesheets/{id}")
    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove); //если нет - иногда посылают 404 Not Found
        //204 No content
    }
}
