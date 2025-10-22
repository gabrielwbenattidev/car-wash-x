package com.gabrielwbenattidev.carwashx.modules.catalog.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gabrielwbenattidev.carwashx.modules.catalog.domain.enums.ItemType;
import com.gabrielwbenattidev.carwashx.modules.tenant.domain.Tenant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "catalog_items")
@Data
public class CatalogItem {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ItemType type;

    @Column(length = 255, nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    // service specific
    @Column(name = "estimated_duration_minutes")
    private Integer estimatedDurationMinutes;

    // product specific
    @Column(name = "track_inventory")
    private Boolean trackInventory = false;

    @Column(name = "current_stock", precision = 10, scale = 2)
    private BigDecimal currentStock;

    @Column(name = "minimum_stock", precision = 10, scale = 2)
    private BigDecimal minimumStock;

    @Column(name = "maximum_stock", precision = 10, scale = 2)
    private BigDecimal maximumStock;

    @Column
    private String unit; // e.g., kg, liters, units

    // control
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "display_order")
    private Integer displayOrder = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CatalogItem parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CatalogItem> composition;

}
