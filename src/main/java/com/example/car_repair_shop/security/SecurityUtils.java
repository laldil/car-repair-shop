package com.example.car_repair_shop.security;

import com.example.car_repair_shop.entity.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Map;

public class SecurityUtils {

    public static Long getCurrentId() {
        var authInfo = getAuthInfo();
        return (Long) ((Map<String, Object>) authInfo.getDetails()).get("id");
    }

    public static String getCurrentUsername() {
        var authInfo = getAuthInfo();
        return (String) ((Map<String, Object>) authInfo.getDetails()).get("username");
    }

    public static boolean hasRole(Role role) {
        var authInfo = getAuthInfo();
        Collection<? extends GrantedAuthority> authorities = authInfo.getAuthorities();
        return authorities.stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals(role.name()));
    }

    public static Authentication getAuthInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

