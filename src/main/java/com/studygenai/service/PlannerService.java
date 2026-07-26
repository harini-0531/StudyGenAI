package com.studygenai.service;

import org.springframework.stereotype.Service;

@Service
public class PlannerService {

    public String generatePlan(
            String subjects,
            int studyHours,
            String examDate,
            String preferredTime) {

        StringBuilder plan = new StringBuilder();

        plan.append("Study Plan\n\n");

        plan.append("Preferred Study Time: ")
                .append(preferredTime)
                .append("\n\n");

        String[] subjectList =
                subjects.split(",");

        for (int i = 0; i < subjectList.length; i++) {

            plan.append("Day ")
                    .append(i + 1)
                    .append(" (")
                    .append(preferredTime)
                    .append("): ")
                    .append(subjectList[i].trim())
                    .append(" - ")
                    .append(studyHours)
                    .append(" Hours\n");
        }

        plan.append("\nExam Date: ")
                .append(examDate);

        return plan.toString();
    }
}