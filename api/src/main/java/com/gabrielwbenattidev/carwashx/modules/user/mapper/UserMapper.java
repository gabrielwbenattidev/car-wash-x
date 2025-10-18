package com.gabrielwbenattidev.carwashx.modules.user.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gabrielwbenattidev.carwashx.common.config.SecurityConfig;
import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.request.CreateTenantRequest;
import com.gabrielwbenattidev.carwashx.modules.user.domain.User;
import com.gabrielwbenattidev.carwashx.modules.user.domain.enums.UserRole;

@Component
public class UserMapper {

    @Autowired
    private SecurityConfig securityConfig;

    public User toAdminUserEntity(CreateTenantRequest request, Tenant tenant) {
        if (request == null || tenant == null) {
            return null;
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getUserEmail());
        user.setEmail(request.getUserEmail());
        user.setPasswordHash(securityConfig.passwordEncoder().encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(UserRole.ADMIN);
        user.setTenant(tenant);

        return user;
    }

}
