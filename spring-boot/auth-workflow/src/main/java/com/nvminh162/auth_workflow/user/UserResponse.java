package com.nvminh162.auth_workflow.user;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String fullName,
        String email,
        Role role
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }
}
