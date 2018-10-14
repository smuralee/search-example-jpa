package com.search.model.domain.filters.core;

import com.search.model.view.ViewModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * <p>
 * Builder of the type T for search criteria
 * </p>
 *
 * @param <T> - Entity type to be queried
 */
@Getter
@AllArgsConstructor
public class PredicateBuilder<T> {

    private ViewModel viewModel;
    private CriteriaBuilder criteriaBuilder;
    private Root<T> entityRoot;
    private Root<T> countRoot;
    private List<Predicate> entityPredicates;
    private List<Predicate> countPredicates;

    /**
     * <p>
     * Apply the view model of the incoming request
     * </p>
     *
     * @param viewModel - the incoming view model from user
     * @return the predicate builder
     */
    public PredicateBuilder<T> forSearchOf(final ViewModel viewModel) {
        this.viewModel = viewModel;
        return this;
    }

    /**
     * <p>
     * The criteria builder for the root entity
     * </p>
     *
     * @param criteriaBuilder - The criteria builder of JPA
     * @return the predicate builder
     */
    public PredicateBuilder<T> withCriteriaBuilder(final CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
        return this;
    }

    /**
     * <p>
     * Root entity of the request
     * </p>
     *
     * @param entityRoot - Root entity
     * @return - Predicate builder
     */
    public PredicateBuilder<T> havingEntityRootOf(final Root<T> entityRoot) {
        this.entityRoot = entityRoot;
        return this;
    }

    /**
     * <p>
     * Root entity for the count of type T
     * </p>
     *
     * @param countRoot - total count root entity
     * @return - Predicate builder
     */
    public PredicateBuilder<T> havingCountRootOf(final Root<T> countRoot) {
        this.countRoot = countRoot;
        return this;
    }

    /**
     * <p>
     * List to store the predicates for the root entity
     * </p>
     *
     * @param entityPredicates - List of predicates
     * @return - Predicate builder
     */
    public PredicateBuilder<T> andEntityPredicateListOf(final List<Predicate> entityPredicates) {
        this.entityPredicates = entityPredicates;
        return this;
    }

    /**
     * <p>
     * List to store the predicates of the total count root
     * </p>
     *
     * @param countPredicates - List of predicates
     * @return - Predicate builder
     */
    public PredicateBuilder<T> andCountPredicateListOf(final List<Predicate> countPredicates) {
        this.countPredicates = countPredicates;
        return this;
    }

    /**
     * <p>
     * Add the entity predicate
     * </p>
     *
     * @param predicate - Entity root restriction/ predicate
     */
    public void addEntityPredicate(final Predicate predicate) {
        this.getEntityPredicates().add(predicate);
    }

    /**
     * <p>
     * Add the count predicate
     * </p>
     *
     * @param predicate - Count root restriction/ predicate
     */
    public void addCountPredicate(final Predicate predicate) {
        this.getCountPredicates().add(predicate);
    }

}
