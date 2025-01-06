package com.example.timesheet.pages;

import com.example.timesheet.service.TimesheetPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService timesheetPageService;

    @GetMapping
    public String getAllTimesheets(Model model) {
        List<TimesheetPageDto> timesheets = timesheetPageService.findAll();
        model.addAttribute("timesheets", timesheets);
        return "timesheets-page.html";
    }
    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model) {
        Optional<TimesheetPageDto> timesheetOpt = timesheetPageService.findById(id);
        if (timesheetOpt.isEmpty()) {
            return "/not-found.html";
            //FIXME return not-found.html
           // throw new NoSuchElementException();
        }

        model.addAttribute("timesheet", timesheetOpt.get());
        return "timesheet-page.html";
    }

    @GetMapping("/project/{projectId}")
    public String getProjectPage(@PathVariable Long projectId, Model model) {
        Optional<TimesheetPageDto> projectOpt = timesheetPageService.findById(projectId);
        if (projectOpt.isEmpty()) {
            throw new NoSuchElementException();
        }
        model.addAttribute("project", projectOpt.get());
        return "project-page.html";
    }
}
