package com.search.model.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Integer id;
    private String name;
    private String brand;

    private List<Location> locations;
    private List<Supplier> suppliers;
}
