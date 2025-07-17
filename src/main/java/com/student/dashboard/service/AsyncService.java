package com.student.dashboard.service;

import com.student.dashboard.model.ImportJob;
import com.student.dashboard.repository.ImportJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
@Service
@RequiredArgsConstructor
public class AsyncService {

    private final ImportJobRepository importJobRepository;

    @Async
    public void processFileAsync(MultipartFile file, String jobId) {
        ImportJob job = importJobRepository.findById(jobId).orElseThrow();

        try {
            job.setStatus("IN_PROGRESS");
            importJobRepository.save(job);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Thread.sleep(50);
                }
            }

            job.setStatus("COMPLETED");
            job.setMessage("Import successful");
        } catch (Exception e) {
            job.setStatus("FAILED");
            job.setMessage("Error: " + e.getMessage());
        } finally {
            importJobRepository.save(job);
        }
    }
}
