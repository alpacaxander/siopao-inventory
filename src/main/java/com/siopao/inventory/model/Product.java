package com.siopao.inventory.model;

import com.yahoo.elide.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@SharePermission
@Entity
@Include(type = "product", rootLevel = true)
@Table(name = "product")
public class Product {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private UUID id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String currency; // EG pound

    @NotNull
    private String nation; // EG England

    @NotNull
    private String era; // EG Victorian

    @NotNull
    private BigDecimal denomination;

    @NotNull
    private String unit;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Set<Coin> coins;
}
