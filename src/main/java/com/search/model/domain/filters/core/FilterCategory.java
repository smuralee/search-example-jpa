package com.search.model.domain.filters.core;

import java.util.function.Supplier;

public enum FilterCategory {
    LIKE(LikeCriteriaFilter::new),
    IN(InCriteriaFilter::new),
    EQUAL(EqualCriteriaFilter::new);

    private Supplier<CriteriaFilter> factory;

    FilterCategory(final Supplier<CriteriaFilter> factory) {
        this.factory = factory;
    }
}
