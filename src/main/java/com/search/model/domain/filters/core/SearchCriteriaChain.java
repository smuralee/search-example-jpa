package com.search.model.domain.filters.core;

public abstract class SearchCriteriaChain<T> {

    private SearchCriteriaChain<T> searchCriteriaChain;

    public void setNextChain(final SearchCriteriaChain<T> chain) {
        this.searchCriteriaChain = chain;
    }

    public void apply(final PredicateBuilder<T> builder) {
        this.applyPredicate(builder);
        if (null != searchCriteriaChain) {
            this.searchCriteriaChain.apply(builder);
        }
    }

    protected abstract void applyPredicate(final PredicateBuilder<T> builder);
}
