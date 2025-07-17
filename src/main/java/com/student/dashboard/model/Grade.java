package com.student.dashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp = "A|B|C|D|F")
    private String grade;

    @OneToOne
    private Enrollment enrollment;
}
