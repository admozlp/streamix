package com.streamix.user.constant;

public class SecurityConstant {

    private SecurityConstant() {
    }

    public static final String[] PUBLIC_ENDPOINTS = {
            "/users/register",
            "/users/login",
            "/actuator/**",
            "/h2-console/**"
    };

    public static final String ROLE_HIERARCHY = "ROLE_ADMIN > ROLE_MODERATOR \n ROLE_MODERATOR > ROLE_USER";

    public static final String DEFAULT_ROLE_PREFIX = "ROLE_";
} 