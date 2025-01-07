package com.example.timesheet;

import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.model.User;
import com.example.timesheet.model.UserRole;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import com.example.timesheet.repository.UserRepository;
import com.example.timesheet.repository.UserRoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


@SpringBootApplication
@OpenAPIDefinition
public class TimesheetApplication {

	public static void main(String[] args) {

//		SpringApplication.run(TimesheetApplication.class, args);

		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);
		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);

		UserRepository userRepo = ctx.getBean(UserRepository.class);
		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("$2a$12$pODQnAlPxWieuZzrc.u95e5onaj6cOEtc2uVzbGEZ5S/tP.O26z86");

		User user = new User();
		user.setLogin("user");
		user.setPassword("user");

		User rest = new User();
		rest.setLogin("rest");
		rest.setPassword("rest");
		admin = userRepo.save(admin);
		user = userRepo.save(user);
		rest = userRepo.save(rest);

		UserRoleRepository userRoleRepository = ctx.getBean(UserRoleRepository.class);
		UserRole adminAdminRole = new UserRole();
		adminAdminRole.setUserId(admin.getId());
		//adminAdminRole.setRoleId(Long.valueOf(admin.getLogin()));
		userRoleRepository.save(adminAdminRole);

		UserRole adminUserRole = new UserRole();
		adminUserRole.setUserId(user.getId());
		//adminUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(adminUserRole);

		UserRole userRestRole = new UserRole();
		userRestRole.setUserId(rest.getId());
		//userUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(userRestRole);

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
