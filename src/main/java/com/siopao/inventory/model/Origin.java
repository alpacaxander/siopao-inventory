package com.siopao.inventory.model;

import com.yahoo.elide.annotation.Include;

import javax.persistence.*;
import java.util.*;

@Include(type = "origin", rootLevel = true)
@Entity
public class Origin {
    @Id
    @Column(name = "origin_id")
    private String id = "";

    private String currency = ""; // USD, YEN, ?

    private String place = "";

    private String era = "";

    private String description = "";

    @OneToMany(mappedBy = "origin")
    private Set<Product> products = new HashSet<>();
}
