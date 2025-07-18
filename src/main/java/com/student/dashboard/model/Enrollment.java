package com.student.dashboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @OneToOne(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Grade grade;

    private boolean completed;
}
