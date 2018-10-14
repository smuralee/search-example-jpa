package com.search.model.domain.filters.chain;

import com.search.model.domain.filters.ProductIdFilter;
import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.domain.filters.core.SearchCriteriaChain;
import com.search.model.view.SearchRequest;
import com.search.persistence.entities.Product;

import javax.persistence.criteria.CriteriaBuilder;

public class ProductIdProcessor implements SearchCriteriaChain<Product> {

    private SearchCriteriaChain<Product> chain;

    @Override
    public void setNextChain(SearchCriteriaChain<Product> chain) {
        this.chain = chain;
    }

    @Override
    public void applyPredicate(PredicateBuilder<Product> builder) {
        final SearchRequest searchRequest = (SearchRequest) builder.getViewModel();
        final CriteriaBuilder criteriaBuilder = builder.getCriteriaBuilder();
        if (null != searchRequest.getProductId()) {
            builder.addEntityPredicate((new ProductIdFilter(searchRequest).apply(criteriaBuilder, builder.getEntityRoot())));
            builder.addCountPredicate(new ProductIdFilter(searchRequest).apply(criteriaBuilder, builder.getCountRoot()));
        }

        this.chain.applyPredicate(builder);
    }
}
