package com.gabrielwbenattidev.carwashx.modules.tenant.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tenants")
@Data
public class Tenant {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "business_name", length = 255, nullable = false)
    private String businessName;

    @Column(name = "trade_name", length = 255)
    private String tradeName;

    @Column(name = "tax_id", length = 20, nullable = false, unique = true)
    private String taxId;

    // contact
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String phone;

    // control
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

}
