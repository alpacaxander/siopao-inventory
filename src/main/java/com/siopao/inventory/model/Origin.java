package com.siopao.inventory.model;

import com.yahoo.elide.annotation.Include;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Include(type = "origin", rootLevel = true)
@Table(name = "origin")
public class Origin {
    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "origin_id")
    private String id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String currency; // EG pound

    @NotNull
    private String nation; // EG England

    @NotNull
    private String era; // EG Victorian

    @OneToMany(mappedBy = "origin")
    private Set<Product> products;
}
