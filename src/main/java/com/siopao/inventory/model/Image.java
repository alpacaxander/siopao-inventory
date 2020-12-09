package com.siopao.inventory.model;

import com.yahoo.elide.annotation.*;
import com.yahoo.elide.core.RequestScope;
import org.springframework.web.client.RestTemplate;

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

    private String name;

    @Transient
    @ComputedAttribute
    public String getData(RequestScope requestScope) {
        return new RestTemplate().getForObject("http://files:8080/file/" + this.id, String.class);
    }

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
