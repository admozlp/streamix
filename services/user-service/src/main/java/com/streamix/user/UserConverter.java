package com.streamix.user;

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
}
