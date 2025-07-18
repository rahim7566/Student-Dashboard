package com.student.dashboard.controller;

import com.student.dashboard.service.ImportService;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class FileUploadController {

    private final ImportService importService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String jobId = importService.importFile(file);
        return ResponseEntity.ok("Import started. Job ID: " + jobId);
    }

    @GetMapping("/status/{jobId}")
    public ResponseEntity<String> checkStatus(@PathVariable String jobId) {
        return ResponseEntity.ok(importService.getStatus(jobId));
    }
}