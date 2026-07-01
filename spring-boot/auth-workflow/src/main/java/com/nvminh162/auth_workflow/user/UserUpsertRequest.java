package com.nvminh162.auth_workflow.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpsertRequest(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        @Size(min = 6) String password,
        Role role
) {
}
