package com.siopao.inventory.model;

import com.yahoo.elide.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@SharePermission
@Entity
@Include(type = "product", rootLevel = true)
@Table(name = "product")
public class Product {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

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

    @OneToMany(mappedBy = "product")
    private Set<Coin> coins;
}
