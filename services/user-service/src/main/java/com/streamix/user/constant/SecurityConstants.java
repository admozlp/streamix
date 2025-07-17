package com.streamix.user.constant;

public class SecurityConstants {

    private SecurityConstants() {
    }

    public static final String[] PUBLIC_ENDPOINTS = {
//            "/users/register",
            "/users/hello",
            "/actuator/**",
            "/h2-console/**"
    };

    public static final String ROLE_HIERARCHY = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
} 