package com.search.model.domain.filters;

import com.search.model.domain.filters.core.InCriteriaFilter;
import com.search.model.view.SearchRequest;
import com.search.model.view.ViewModel;
import com.search.persistence.entities.SupplyChain;
import com.search.util.StringConstants;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class RegionIdFilter extends InCriteriaFilter<SupplyChain> {

    private ViewModel request;

    @Override
    protected Predicate getInAttribute(Root<SupplyChain> root) {
        return root.get(StringConstants.ID).in(((SearchRequest) request).getRegionIds());
    }
}
