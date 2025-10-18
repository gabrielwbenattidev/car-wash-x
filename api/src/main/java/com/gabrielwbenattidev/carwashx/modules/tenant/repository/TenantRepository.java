package com.gabrielwbenattidev.carwashx.modules.tenant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    Boolean existsByEmail(String email);

    Boolean existsByTaxId(String taxId);

}
