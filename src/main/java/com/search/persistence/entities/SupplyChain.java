package com.search.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SUPPLY_CHAIN")
@Data
public class SupplyChain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "supplyChains")
    private Set<Product> products = new HashSet<>();

}
