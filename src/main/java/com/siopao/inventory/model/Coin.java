package com.siopao.inventory.model;

import com.yahoo.elide.annotation.Include;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Include(type = "coin")
@Entity
public class Coin {
    @Id
    @Column(name = "coin_id")
    private String id = "";

    private String status = ""; // TODO set as enum

    private String location = ""; // TODO create physical location format

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
//    private String images = ""; // TODO blobs/ multiple images
}
