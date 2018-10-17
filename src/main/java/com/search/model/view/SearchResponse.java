package com.search.model.view;

import com.search.model.domain.Item;
import lombok.Value;

import java.util.List;

/**
 * <p>
 * Value object for the search response
 * </p>
 */
@Value
public class SearchResponse implements ViewModel {
    private List<Item> items;
    private Long totalCount;
}
