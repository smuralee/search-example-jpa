package com.search.model.domain.filters.chain;

import com.search.model.domain.filters.ItemNameFilter;
import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.domain.filters.core.SearchCriteriaChain;
import com.search.model.view.SearchRequest;
import com.search.persistence.entities.Product;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * <p>
 * Applies the item name filter
 * </p>
 */
public class ItemNameProcessor extends SearchCriteriaChain<Product> {

    @Override
    public void applyPredicate(PredicateBuilder<Product> builder) {
        final SearchRequest searchRequest = (SearchRequest) builder.getViewModel();
        final CriteriaBuilder criteriaBuilder = builder.getCriteriaBuilder();
        if (null != searchRequest.getItemName()) {
            builder.addEntityPredicate((new ItemNameFilter(searchRequest).apply(criteriaBuilder, builder.getEntityRoot())));
            builder.addCountPredicate(new ItemNameFilter(searchRequest).apply(criteriaBuilder, builder.getCountRoot()));
        }
    }
}
