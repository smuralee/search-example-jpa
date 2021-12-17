package com.smuralee.model.domain.filters.chain;

import com.smuralee.model.domain.filters.ProductSkuCodeFilter;
import com.smuralee.model.domain.filters.core.PredicateBuilder;
import com.smuralee.model.domain.filters.core.SearchCriteriaChain;
import com.smuralee.model.view.SearchRequest;
import com.smuralee.persistence.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;

/**
 * <p>
 * Applies the item id filter
 * </p>
 */
public class ProductSkuCodeProcessor extends SearchCriteriaChain<Product> {

    @Override
    protected void applyPredicate(PredicateBuilder<Product> builder) {
        final SearchRequest searchRequest = (SearchRequest) builder.getViewModel();
        final CriteriaBuilder criteriaBuilder = builder.getCriteriaBuilder();
        if (null != searchRequest.productSkuCode()) {
            builder.addEntityPredicate((new ProductSkuCodeFilter(searchRequest).apply(criteriaBuilder, builder.getEntityRoot())));
            builder.addCountPredicate(new ProductSkuCodeFilter(searchRequest).apply(criteriaBuilder, builder.getCountRoot()));
        }
    }
}
