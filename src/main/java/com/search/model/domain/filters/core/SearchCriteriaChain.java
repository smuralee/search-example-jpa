package com.search.model.domain.filters.core;

/**
 * <p>
 * Abstract implementation of the filter chain for the incoming request
 * </p>
 *
 * @param <T> - Entity type
 */
public abstract class SearchCriteriaChain<T> {

    private SearchCriteriaChain<T> chain;

    /**
     * <p>
     * Sets the next chain in the process
     * </p>
     *
     * @param chain - Next step in the chain
     */
    public void setNextChain(final SearchCriteriaChain<T> chain) {
        this.chain = chain;
    }

    /**
     * <p>
     * Applies the predicates
     * </p>
     *
     * @param builder - PredicateBuilder for type of entity
     */
    public void apply(final PredicateBuilder<T> builder) {
        this.applyPredicate(builder);
        if (null != chain) {
            this.chain.apply(builder);
        }
    }

    /**
     * <p>
     * Implementation of the predicate for each step
     * </p>
     *
     * @param builder - PredicateBuilder for the type of entity
     */
    protected abstract void applyPredicate(final PredicateBuilder<T> builder);
}
