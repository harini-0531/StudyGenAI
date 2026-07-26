package com.studygenai.controller;

import com.studygenai.dto.SummaryResponse;
import com.studygenai.service.SummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary/{id}")
    public SummaryResponse getSummary(
            @PathVariable Long id,
            @RequestParam String type) {

        String summary =
                summaryService.generateSummary(
                        id,
                        type
                );

        return new SummaryResponse(summary);
    }
}