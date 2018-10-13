package com.search.model.domain.filters.core;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * <p>
 * Applying the predicates for criteria
 * </p>
 *
 * @param <T>
 */
@FunctionalInterface
public interface CriteriaFilter<T> {
    /**
     * <p>
     * Predicate for the {@link Root}
     * </p>
     *
     * @param criteriaBuilder - Criteria builder
     * @param root            - Root object
     * @return Predicate
     */
    Predicate apply(final CriteriaBuilder criteriaBuilder, final Root<T> root);
}
