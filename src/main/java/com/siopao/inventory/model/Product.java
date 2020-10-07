package com.siopao.inventory.model;

import com.yahoo.elide.annotation.Include;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Include(type = "product", rootLevel = true)
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    private String id = "";

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin = null;

    @OneToMany(mappedBy = "product")
    private Set<Coin> coins = new HashSet<>();
}
