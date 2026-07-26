package com.studygenai.controller;

import com.studygenai.service.PlannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planner")
@CrossOrigin(origins = "*")
public class PlannerController {

    @Autowired
    private PlannerService plannerService;

    @PostMapping
    public String generatePlan(
            @RequestParam String subjects,
            @RequestParam int studyHours,
            @RequestParam String examDate,
            @RequestParam String preferredTime) {

        return plannerService.generatePlan(
                subjects,
                studyHours,
                examDate,
                preferredTime
        );
    }
}