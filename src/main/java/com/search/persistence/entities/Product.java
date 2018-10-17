package com.search.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String description;

    @NotNull
    private String manufacturer;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "REGION_REF",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "REGION_ID")}
    )
    Set<Region> regions = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "SUPPLY_CHAIN_REF",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SUPPLY_CHAIN_ID")}
    )
    Set<SupplyChain> supplyChains = new HashSet<>();

}
