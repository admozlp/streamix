package com.streamix.user.converter;

import com.streamix.user.dto.LoginResponse;
import com.streamix.user.dto.UserResponse;
import com.streamix.user.model.User;
import com.streamix.user.security.CustomUserDetails;

public class UserConverter {
    private UserConverter() {
    }

    public static CustomUserDetails toCustomUserDetails(com.streamix.user.model.User user) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setEmail(user.getEmail());
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(user.isEnabled());
        userDetails.setRoles(user.getRoles());
        return userDetails;
    }

    public static LoginResponse toLoginResponse(String token, String refreshToken) {
        return new LoginResponse(token, refreshToken);
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.isEnabled());
    }
}
