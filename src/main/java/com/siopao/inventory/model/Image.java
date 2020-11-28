package com.siopao.inventory.model;

import com.yahoo.elide.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@SharePermission
@Entity
@Include(type = "image")
@Table(name = "image")
public class Image {
    @Id
    @NotNull
    @Column(name = "image_id")
    private UUID id;

    private Integer index;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
