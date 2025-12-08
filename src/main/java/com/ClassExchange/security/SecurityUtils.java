package com.ClassExchange.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    private SecurityUtils() {}

    public static String currentCampusId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;
        Object principal = auth.getPrincipal();
        if (principal instanceof AuthenticatedUser au) {
            return au.getCampusId();
        }
        return null;
    }

    public static boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        Object principal = auth.getPrincipal();
        if (principal instanceof AuthenticatedUser au) {
            return au.getRoles() != null && au.getRoles().stream().anyMatch(r -> "ADMINISTRADOR".equals(r));
        }
        return false;
    }
}

