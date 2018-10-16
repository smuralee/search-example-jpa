package com.search.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "supplyChain", cascade = {CascadeType.ALL})
    @JsonIgnore
    private Set<SupplyChainRef> supplyChainRefs = new HashSet<>();

}
