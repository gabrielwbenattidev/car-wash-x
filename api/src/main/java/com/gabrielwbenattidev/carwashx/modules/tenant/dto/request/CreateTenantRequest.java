package com.gabrielwbenattidev.carwashx.modules.tenant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTenantRequest {

    // tenant info
    private String businessName;

    private String tradeName;

    private String taxId;

    private String tenantEmail;

    private String tenantPhone;

    // user info
    private String username;

    private String userEmail;

    private String password;

    private String fullName;

}
