package com.gabrielwbenattidev.carwashx.modules.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.request.CreateTenantRequest;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.response.TenantIdResponse;
import com.gabrielwbenattidev.carwashx.modules.tenant.mapper.TenantMapper;
import com.gabrielwbenattidev.carwashx.modules.tenant.repository.TenantRepository;
import com.gabrielwbenattidev.carwashx.modules.user.domain.User;
import com.gabrielwbenattidev.carwashx.modules.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TenantMapper tenantMapper;

    @Transactional
    public TenantIdResponse registerTenantWithAdmin(CreateTenantRequest requestBody) {
        if (requestBody == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        validateTenantDoesNotExists(requestBody.getTenantEmail(), requestBody.getTaxId());

        validareUserAdminDoesNotExists(requestBody.getUserEmail());

        Tenant tenant = tenantMapper.toEntity(requestBody);
        tenant.setIsActive(true);
        tenant = tenantRepository.save(tenant);

        User userAdmin = tenantMapper.toAdminUserEntity(requestBody, tenant);
        userAdmin = userRepository.save(userAdmin);

        return tenantMapper.toIdResponse(tenant);
    }

    private void validateTenantDoesNotExists(String email, String taxId) {
        if (tenantRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (tenantRepository.existsByTaxId(taxId)) {
            throw new IllegalArgumentException("Tax ID already in use");
        }
    }

    private void validareUserAdminDoesNotExists(String userEmail) {
        if (userRepository.existsByEmail(userEmail)) {
            throw new IllegalArgumentException("User email already in use");
        }
    }

}
