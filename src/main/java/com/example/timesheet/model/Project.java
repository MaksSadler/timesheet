package com.example.timesheet.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Project {
    private Long projectId;
    private String projectName;
}
