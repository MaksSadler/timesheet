package com.example.timesheet;

import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);
		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);

		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setProjectName("Project #" + i);
			projectRepo.save(project);
		}

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);
			Timesheet timesheet = new Timesheet();
			timesheet.setTimesheetProjectId(ThreadLocalRandom.current().nextLong(1,6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}
	}

}
