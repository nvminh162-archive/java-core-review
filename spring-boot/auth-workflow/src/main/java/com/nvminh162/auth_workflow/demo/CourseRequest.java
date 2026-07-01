package com.nvminh162.auth_workflow.demo;

import jakarta.validation.constraints.NotBlank;

public record CourseRequest(
        @NotBlank String title,
        String description
) {
}
