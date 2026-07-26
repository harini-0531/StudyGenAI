package com.studygenai.service;

import com.studygenai.model.UploadedDocument;
import com.studygenai.repository.UploadedDocumentRepository;
import com.studygenai.util.PdfUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Service
public class UploadService {

    @Autowired
    private UploadedDocumentRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadPdf(MultipartFile file) throws IOException {

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destination = new File(directory, file.getOriginalFilename());

        Path destinationPath = destination.toPath();

        Files.copy(
                file.getInputStream(),
                destinationPath,
                StandardCopyOption.REPLACE_EXISTING
        );

        String text = PdfUtil.extractText(destination);

        UploadedDocument document = new UploadedDocument();

        document.setFileName(file.getOriginalFilename());
        document.setFilePath(destination.getAbsolutePath());
        document.setExtractedText(text);

        System.out.println("File Name = " + document.getFileName());
        System.out.println("Text Length = " + document.getExtractedText().length());

        UploadedDocument savedDocument = repository.save(document);

        System.out.println("Saved ID = " + savedDocument.getId());
        System.out.println("Total Documents = " + repository.count());
        System.out.println("Document saved into database.");

        return "PDF uploaded successfully.";
    }

    public List<UploadedDocument> getAllDocuments() {
        return repository.findAll();
    }

public Long getLatestDocumentId() {

    List<UploadedDocument> documents =
            repository.findAll();

    if(documents.isEmpty()) {

        throw new RuntimeException(
                "No documents uploaded"
        );
    }

    return documents.get(
            documents.size() - 1
    ).getId();

}
}
