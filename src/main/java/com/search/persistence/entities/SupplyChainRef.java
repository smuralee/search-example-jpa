package com.search.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SUPPLY_CHAIN_REF")
@Data
public class SupplyChainRef implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SUPPLY_CHAIN_ID")
    private SupplyChain supplyChain;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
