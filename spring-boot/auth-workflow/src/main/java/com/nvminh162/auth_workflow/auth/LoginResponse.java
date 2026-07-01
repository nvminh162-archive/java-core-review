package com.nvminh162.auth_workflow.auth;

import com.nvminh162.auth_workflow.user.UserResponse;

public record LoginResponse(
        String accessToken,
        String tokenType,
        UserResponse user
) {
}
