package com.gabrielwbenattidev.carwashx.modules.vehicle.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gabrielwbenattidev.carwashx.modules.customer.domain.Customer;
import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "vehicles", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "tenant_id", "plate" })
})
@Data
public class Vehicle {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 20, nullable = false)
    private String plate;

    // details
    @Column(length = 100)
    private String brand;

    @Column(length = 100)
    private String model;

    @Column(length = 50)
    private String color;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Column(name = "model_year")
    private Integer modelYear;

    // registration
    @Column(length = 50)
    private String renavam;

    @Column(length = 50)
    private String chassis;

    // control
    @Lob
    private String notes;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
