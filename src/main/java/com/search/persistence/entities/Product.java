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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private Set<SupplyChainRef> supplyChainRefs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private Set<RegionRef> regionRefs = new HashSet<>();

}
