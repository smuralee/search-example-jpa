package com.search.model.domain.filters.chain;

import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.domain.filters.core.SearchCriteriaChain;
import com.search.persistence.entities.Product;

public class ProductFilterChain {

    private SearchCriteriaChain<Product> step1;

    public ProductFilterChain() {

        // Initialize the steps
        step1 = new ProductIdProcessor();
        SearchCriteriaChain<Product> step2 = new ProductDescriptionProcessor();
        SearchCriteriaChain<Product> step3 = new ProductManufacturerProcessor();

        // Setting the filter chaining
        step1.setNextChain(step2);
        step2.setNextChain(step3);
    }

    public void applyPredicates(PredicateBuilder<Product> builder) {
        this.step1.apply(builder);
    }


}
