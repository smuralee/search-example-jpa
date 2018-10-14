package com.search.model.view;

import com.search.persistence.entities.Product;
import lombok.Value;

import java.util.List;

/**
 * <p>
 * Value object for the search response
 * </p>
 */
@Value
public class SearchResponse implements ViewModel {
    private List<Product> products;
    private Long totalCount;
}
