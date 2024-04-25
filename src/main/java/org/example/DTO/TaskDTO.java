package org.example.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private String title;
    private String note;
    private Date due_date;
}
