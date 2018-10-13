package com.search.model.view;

import com.search.model.domain.SortCriteria;
import com.search.model.domain.filters.core.FilterCategory;
import com.search.model.domain.filters.core.SearchFilter;
import lombok.Value;

import java.util.List;

@Value
public class SearchRequest {

    @SearchFilter(type = FilterCategory.EQUAL)
    private Integer productId;

    @SearchFilter(type = FilterCategory.LIKE)
    private String productName;

    @SearchFilter(type = FilterCategory.LIKE)
    private String productDescription;

    @SearchFilter(type = FilterCategory.LIKE)
    private String productManufacturer;

    @SearchFilter(type = FilterCategory.IN)
    private List<Integer> productLineIds;

    @SearchFilter(type = FilterCategory.IN)
    private List<Integer> regionIds;

    private List<SortCriteria> sortList;
}
