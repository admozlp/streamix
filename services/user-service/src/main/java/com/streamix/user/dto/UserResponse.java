package com.streamix.user.dto;

import java.util.UUID;

public record UserResponse(UUID id, String email, String name, boolean enabled) {
}
