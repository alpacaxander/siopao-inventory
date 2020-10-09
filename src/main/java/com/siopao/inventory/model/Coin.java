package com.siopao.inventory.model;

import com.yahoo.elide.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SharePermission
@Entity
@Include(type = "coin")
@Table(name = "coin")
public class Coin {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_id")
    private Integer id;

    @NotNull
    private String status; // TODO set as enum

    private String location; // TODO create physical location format

    private String condition; // TODO set as enum

    @Column(name = "craigslist_link")
    private String craigslistLink;

    @Column(name = "facebook_link")
    private String facebookLink;

    // TODO images

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
