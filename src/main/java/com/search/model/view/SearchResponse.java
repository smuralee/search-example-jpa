package com.search.model.view;

import com.search.persistence.entities.Product;
import lombok.Value;

import java.util.List;

@Value
public class SearchResponse implements ViewModel {
    private List<Product> products;
}
