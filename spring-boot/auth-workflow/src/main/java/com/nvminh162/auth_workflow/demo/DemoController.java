package com.nvminh162.auth_workflow.demo;

import com.nvminh162.auth_workflow.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @PostMapping("/courses")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ApiResponse<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request, Authentication authentication) {
        CourseResponse response = new CourseResponse(request.title(), request.description(), authentication.getName());
        return ApiResponse.ok("Course created", response);
    }
}
