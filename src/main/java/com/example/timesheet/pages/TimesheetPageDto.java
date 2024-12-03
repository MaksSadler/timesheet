package com.example.timesheet.pages;

import lombok.Data;

@Data
public class TimesheetPageDto {

   private String projectName;
   private String id;
   private String projectId;
   private String minutes;
   private String createdAt;

}
