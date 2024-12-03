package com.example.timesheet.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Timesheet {

    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDate createdAt;


    public Timesheet() {
        this.createdAt = LocalDate.now();
    }
}
