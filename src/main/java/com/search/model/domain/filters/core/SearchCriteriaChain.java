package com.search.model.domain.filters.core;

public interface SearchCriteriaChain<T> {

    void setNextChain(final SearchCriteriaChain<T> chain);

    void applyPredicate(final PredicateBuilder<T> builder);
}
