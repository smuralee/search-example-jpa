package com.search.model.view;

import lombok.Value;

import java.util.List;

@Value
public class SearchRequest {
    private String productName;
    private String productDescription;
    private String productManufacturer;
    private List<Integer> productLineIds;
    private List<Integer> regionIds;
}
