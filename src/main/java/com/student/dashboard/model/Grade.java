package com.student.dashboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "A|B|C|D|F")
    private String grade;

    @OneToOne
    private Enrollment enrollment;
}
