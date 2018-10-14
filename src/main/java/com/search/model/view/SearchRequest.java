package com.search.model.view;

import com.search.model.domain.SortCriteria;
import lombok.Value;

import java.util.List;

@Value
public class SearchRequest implements ViewModel {

    private Integer productId;

    private String productName;

    private String productDescription;

    private String productManufacturer;

    private List<Integer> productLineIds;

    private List<Integer> regionIds;

    private List<SortCriteria> sortList;
}
