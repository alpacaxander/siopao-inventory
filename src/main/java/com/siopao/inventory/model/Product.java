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

    @NotNull
    private BigDecimal denomination;

    @NotNull
    private String unit;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;

    @OneToMany(mappedBy = "product")
    private Set<Coin> coins;
}
