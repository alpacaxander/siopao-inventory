package com.siopao.inventory.model;

import com.yahoo.elide.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@SharePermission
@Entity
@Include(type = "coin", rootLevel = true)
@Table(name = "coin")
public class Coin {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coin_id")
    private UUID id;

    @NotNull
    private String status; // TODO set as enum

    private String location; // TODO create physical location format

    private String condition; // TODO set as enum

    @Column(name = "craigslist_link")
    private String craigslistLink;

    @Column(name = "facebook_link")
    private String facebookLink;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "coin", cascade = CascadeType.REMOVE)
    private Set<Image> images;
}
