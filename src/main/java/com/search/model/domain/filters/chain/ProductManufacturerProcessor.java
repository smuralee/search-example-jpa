package com.search.model.domain.filters.chain;

import com.search.model.domain.filters.ProductManufacturerFilter;
import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.domain.filters.core.SearchCriteriaChain;
import com.search.model.view.SearchRequest;
import com.search.persistence.entities.Product;

import javax.persistence.criteria.CriteriaBuilder;

public class ProductManufacturerProcessor implements SearchCriteriaChain<Product> {

    private SearchCriteriaChain<Product> chain;

    @Override
    public void setNextChain(SearchCriteriaChain<Product> chain) {
        this.chain = chain;
    }

    @Override
    public void applyPredicate(PredicateBuilder<Product> builder) {
        final SearchRequest searchRequest = (SearchRequest) builder.getViewModel();
        final CriteriaBuilder criteriaBuilder = builder.getCriteriaBuilder();
        if (null != searchRequest.getProductManufacturer()) {
            builder.addEntityPredicate((new ProductManufacturerFilter(searchRequest).apply(criteriaBuilder, builder.getEntityRoot())));
            builder.addCountPredicate(new ProductManufacturerFilter(searchRequest).apply(criteriaBuilder, builder.getCountRoot()));
        }

        this.chain.applyPredicate(builder);
    }
}
