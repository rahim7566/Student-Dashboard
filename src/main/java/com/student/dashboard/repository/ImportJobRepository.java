package com.student.dashboard.repository;

import com.student.dashboard.model.ImportJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportJobRepository extends JpaRepository<ImportJob, String> {
}