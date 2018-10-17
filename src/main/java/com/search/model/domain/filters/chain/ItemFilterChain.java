package com.search.model.domain.filters.chain;

import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.domain.filters.core.SearchCriteriaChain;
import com.search.persistence.entities.Product;

/**
 * <p>
 * Configures the sequence of steps in the filter chain
 * </p>
 */
public class ItemFilterChain {

    private SearchCriteriaChain<Product> step1;

    public ItemFilterChain() {

        // Initialize the steps
        step1 = new ItemIdProcessor();
        SearchCriteriaChain<Product> step2 = new ItemNameProcessor();
        SearchCriteriaChain<Product> step3 = new ItemBrandProcessor();

        // Setting the filter chaining
        step1.setNextChain(step2);
        step2.setNextChain(step3);
    }

    /**
     * <p>
     * Initializes the predicate chain
     * </p>
     *
     * @param builder - Predicate builder
     */
    public void applyPredicates(PredicateBuilder<Product> builder) {
        this.step1.apply(builder);
    }


}
