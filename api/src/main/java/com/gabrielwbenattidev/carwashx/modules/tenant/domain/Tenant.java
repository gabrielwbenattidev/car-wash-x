package com.gabrielwbenattidev.carwashx.modules.tenant.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gabrielwbenattidev.carwashx.modules.catalog.domain.CatalogItem;
import com.gabrielwbenattidev.carwashx.modules.customer.domain.Customer;
import com.gabrielwbenattidev.carwashx.modules.user.domain.User;
import com.gabrielwbenattidev.carwashx.modules.vehicle.domain.Vehicle;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    // relationships
    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CatalogItem> catalogItems;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

}
