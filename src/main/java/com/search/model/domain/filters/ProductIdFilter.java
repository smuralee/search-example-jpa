package com.search.model.domain.filters;


import com.search.model.domain.filters.core.EqualCriteriaFilter;
import com.search.model.view.SearchRequest;
import com.search.model.view.ViewModel;
import com.search.persistence.entities.Product;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ProductIdFilter extends EqualCriteriaFilter<Product, Integer>{

    private ViewModel request;

    @Override
    protected Integer getEqualParameter() {
        return ((SearchRequest) this.request).getProductId();
    }

    @Override
    protected Expression<Integer> getEqualityAttribute(Root<Product> root) {
        return root.get("id");
    }
}
