package com.gabrielwbenattidev.carwashx.modules.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielwbenattidev.carwashx.modules.tenant.dto.request.CreateTenantRequest;
import com.gabrielwbenattidev.carwashx.modules.tenant.dto.response.TenantIdResponse;
import com.gabrielwbenattidev.carwashx.modules.tenant.service.TenantService;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/register")
    public ResponseEntity<TenantIdResponse> registerTenant(
            @RequestBody CreateTenantRequest requestBody) {
        try {
            TenantIdResponse tenantIdResponse = tenantService.registerTenantWithAdmin(requestBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(tenantIdResponse);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
