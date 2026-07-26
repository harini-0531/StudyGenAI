package com.studygenai.repository;

import com.studygenai.model.UploadedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedDocumentRepository
        extends JpaRepository<UploadedDocument, Long> {

}