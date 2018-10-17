package com.search.model.view;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * Value object to hold the incoming request body for the search
 * </p>
 */
@Value
public class SearchRequest implements ViewModel {

    private Integer itemId;

    private String itemName;

    private String itemBrand;

    private List<Integer> supplierIds;

    private List<Integer> regionIds;

    @NotNull
    private Integer pageNumber;

    @NotNull
    private Integer pageSize;
}
