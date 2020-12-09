package com.siopao.inventory.model;

import com.siopao.inventory.data.ImageDataHandler;
import com.yahoo.elide.annotation.*;
import com.yahoo.elide.core.RequestScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@SharePermission
@Entity
@Include(type = "image")
@Table(name = "image")
public class Image {
    @Autowired
    @Transient
    ImageDataHandler imageDataHandler;

    @Id
    @NotNull
    @Column(name = "image_id")
    private UUID id;

    private Integer index;

    private String name;

    @Transient
    @ComputedAttribute
    public String getData(RequestScope requestScope) {
        return this.imageDataHandler.get(this.id.toString());
    }

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
