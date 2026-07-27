package com.studygenai.controller;

import com.studygenai.model.UploadedDocument;
import com.studygenai.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://studygenai-h8a7.onrender.com") 
public class UploadController {

    @Autowired
    private UploadService uploadService;

    // Upload PDF
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        System.out.println("======================================");
        System.out.println("UPLOAD API CALLED");
        System.out.println("Received File : " + file.getOriginalFilename());
        System.out.println("File Size     : " + file.getSize() + " bytes");
        System.out.println("======================================");

        return ResponseEntity.ok(uploadService.uploadPdf(file));
    }

    // Get all uploaded documents
    @GetMapping("/documents")
    public List<UploadedDocument> getAllDocuments() {
        return uploadService.getAllDocuments();
    }

    @GetMapping("/latest-document")
    public Long getLatestDocumentId() {

        return uploadService.getLatestDocumentId();
    }
}