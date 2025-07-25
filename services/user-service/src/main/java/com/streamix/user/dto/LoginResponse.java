package com.streamix.user.dto;

public record LoginResponse(String token, String refreshToken) {
}
