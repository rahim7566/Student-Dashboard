package com.student.dashboard.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ImportJob {
    @Id
    private String jobId;
    private String status;
    private String message;
    private LocalDateTime createdAt;
}
