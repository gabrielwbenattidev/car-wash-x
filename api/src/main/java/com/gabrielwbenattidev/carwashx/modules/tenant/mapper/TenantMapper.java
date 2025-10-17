package com.gabrielwbenattidev.carwashx.modules.tenant.mapper;

import org.springframework.stereotype.Component;

import com.gabrielwbenattidev.carwashx.common.util.StringUtils;
import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.request.CreateTenantRequest;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.response.TenantIdResponse;
import com.gabrielwbenattidev.carwashx.modules.user.domain.User;
import com.gabrielwbenattidev.carwashx.modules.user.domain.enums.UserRole;

@Component
public class TenantMapper {

    public Tenant toEntity(CreateTenantRequest request) {
        if (request == null) {
            return null;
        }

        String alphanumericTaxId = StringUtils.onlyAlphanumeric(request.getTaxId());

        Tenant tenant = new Tenant();
        tenant.setBusinessName(request.getBusinessName());
        tenant.setTradeName(request.getTradeName());
        tenant.setTaxId(alphanumericTaxId);
        tenant.setEmail(request.getTenantEmail());
        tenant.setPhone(request.getTenantPhone());

        return tenant;
    }

    public User toAdminUserEntity(CreateTenantRequest request, Tenant tenant) {
        if (request == null || tenant == null) {
            return null;
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getUserEmail());
        user.setPasswordHash(request.getPassword());
        user.setEmail(request.getUserEmail());
        user.setFullName(request.getFullName());
        user.setRole(UserRole.ADMIN);
        user.setTenant(tenant);

        return user;
    }

    public TenantIdResponse toIdResponse(Tenant tenant) {
        if (tenant == null) {
            return null;
        }

        TenantIdResponse response = new TenantIdResponse();
        response.setId(tenant.getId());
        return response;
    }

}
