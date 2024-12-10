package com.example.timesheet.service;


import com.example.timesheet.pages.TimesheetPageDto;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public List<TimesheetPageDto> findAll() {
        return timesheetService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    public Optional<TimesheetPageDto> findById(long id) {
        return timesheetService.findById(id) //Возвращает Optional<Timesheet>
                .map(this::convert);
    }

    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.getProjectById(timesheet.getTimesheetProjectId());

        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
        timesheetPageDto.setProjectName(project.getProjectName());
        timesheetPageDto.setId(String.valueOf(timesheet.getTimesheetId()));
        timesheetPageDto.setProjectId(String.valueOf(timesheet.getTimesheetProjectId()));
        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().toString());

        return timesheetPageDto;
    }

}
