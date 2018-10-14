package com.search.model.domain.filters;

import com.search.model.domain.filters.core.InCriteriaFilter;
import com.search.model.view.SearchRequest;
import com.search.model.view.ViewModel;
import com.search.persistence.entities.ProductLine;
import com.search.util.StringConstants;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ProductLineIdFilter extends InCriteriaFilter<ProductLine> {

    private ViewModel request;

    @Override
    protected Predicate getInAttribute(Root<ProductLine> root) {
        return root.get(StringConstants.ID).in(((SearchRequest) request).getProductLineIds());
    }
}
