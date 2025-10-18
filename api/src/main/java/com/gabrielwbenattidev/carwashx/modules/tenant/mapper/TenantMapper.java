package com.gabrielwbenattidev.carwashx.modules.tenant.mapper;

import org.springframework.stereotype.Component;

import com.gabrielwbenattidev.carwashx.common.util.StringUtils;
import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.request.CreateTenantRequest;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.response.TenantIdResponse;

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

    public TenantIdResponse toIdResponse(Tenant tenant) {
        if (tenant == null) {
            return null;
        }

        TenantIdResponse response = new TenantIdResponse();
        response.setId(tenant.getId());
        return response;
    }

}
