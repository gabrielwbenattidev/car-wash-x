package com.gabrielwbenattidev.carwashx.modules.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.request.CreateTenantRequest;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.response.TenantIdResponse;
import com.gabrielwbenattidev.carwashx.modules.tenant.mapper.TenantMapper;
import com.gabrielwbenattidev.carwashx.modules.tenant.repository.TenantRepository;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TenantMapper tenantMapper;

    public TenantIdResponse registerTenantWithAdmin(CreateTenantRequest requestBody) {
        if (requestBody == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        Tenant tenant = tenantMapper.toEntity(requestBody);

        if (tenantRepository.existsByEmail(tenant.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (tenantRepository.existsByTaxId(tenant.getTaxId())) {
            throw new IllegalArgumentException("Tax ID already in use");
        }

        Tenant savedTenant = tenantRepository.save(tenant);
        return tenantMapper.toIdResponse(savedTenant);
    }

}
