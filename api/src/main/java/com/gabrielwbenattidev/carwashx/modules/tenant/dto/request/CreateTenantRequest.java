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

    private String businessName;

    private String tradeName;

    private String taxId;

    private String email;

    private String phone;

}
