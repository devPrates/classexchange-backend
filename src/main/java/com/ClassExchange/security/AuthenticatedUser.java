package com.ClassExchange.security;

import java.util.List;

public class AuthenticatedUser {
    private final String userId;
    private final String campusId;
    private final List<String> roles;

    public AuthenticatedUser(String userId, String campusId, List<String> roles) {
        this.userId = userId;
        this.campusId = campusId;
        this.roles = roles;
    }

    public String getUserId() { return userId; }
    public String getCampusId() { return campusId; }
    public List<String> getRoles() { return roles; }
}

