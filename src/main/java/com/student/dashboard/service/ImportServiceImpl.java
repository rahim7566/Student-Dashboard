package com.student.dashboard.service;

import com.student.dashboard.model.ImportJob;
import com.student.dashboard.repository.ImportJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {

    private final ImportJobRepository importJobRepository;
    private final AsyncService asyncService;

    @Override
    public String importFile(MultipartFile file) {
        String jobId = UUID.randomUUID().toString();

        ImportJob job = new ImportJob();
        job.setJobId(jobId);
        job.setStatus("PENDING");
        job.setCreatedAt(LocalDateTime.now());
        importJobRepository.save(job);

        asyncService.processFileAsync(file, jobId);

        return jobId;
    }

    @Override
    public String getStatus(String jobId) {
        return importJobRepository.findById(jobId)
                .map(job -> "Status: " + job.getStatus() + " - " + job.getMessage())
                .orElse("Job ID not found");
    }


}