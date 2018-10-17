package com.search.model.domain.filters.chain;

import com.search.model.domain.filters.ItemIdFilter;
import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.domain.filters.core.SearchCriteriaChain;
import com.search.model.view.SearchRequest;
import com.search.persistence.entities.Product;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * <p>
 * Applies the product id filter
 * </p>
 */
public class ProductIdProcessor extends SearchCriteriaChain<Product> {

    @Override
    protected void applyPredicate(PredicateBuilder<Product> builder) {
        final SearchRequest searchRequest = (SearchRequest) builder.getViewModel();
        final CriteriaBuilder criteriaBuilder = builder.getCriteriaBuilder();
        if (null != searchRequest.getItemId()) {
            builder.addEntityPredicate((new ItemIdFilter(searchRequest).apply(criteriaBuilder, builder.getEntityRoot())));
            builder.addCountPredicate(new ItemIdFilter(searchRequest).apply(criteriaBuilder, builder.getCountRoot()));
        }
    }
}
