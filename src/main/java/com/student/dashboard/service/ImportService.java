package com.student.dashboard.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {

    String importFile(MultipartFile file);

    String getStatus(String jobId);
}