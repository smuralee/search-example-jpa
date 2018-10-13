package com.search.model.domain.filters;


import com.search.model.domain.filters.core.InCriteriaFilter;
import com.search.persistence.entities.Product;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductIdFilter extends InCriteriaFilter<Product> {

    @Override
    protected Predicate getInAttribute(Root<Product> root) {
        return root.get("id").in(1, 2);
    }

}
