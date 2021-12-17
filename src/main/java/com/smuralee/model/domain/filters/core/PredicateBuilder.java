/**
 * Copyright 2018 Suraj Muraleedharan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smuralee.model.domain.filters.core;

import com.smuralee.model.view.ViewModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * <p>
 * Builder of the type T for smuralee criteria
 * </p>
 *
 * @param <T> - Entity type to be queried
 */

public class PredicateBuilder<T> {

    private ViewModel viewModel;
    private CriteriaBuilder criteriaBuilder;
    private Root<T> entityRoot;
    private Root<T> countRoot;
    private List<Predicate> entityPredicates;
    private List<Predicate> countPredicates;

    public PredicateBuilder() {
    }

    public PredicateBuilder(ViewModel viewModel, CriteriaBuilder criteriaBuilder, Root<T> entityRoot, Root<T> countRoot, List<Predicate> entityPredicates, List<Predicate> countPredicates) {
        this.viewModel = viewModel;
        this.criteriaBuilder = criteriaBuilder;
        this.entityRoot = entityRoot;
        this.countRoot = countRoot;
        this.entityPredicates = entityPredicates;
        this.countPredicates = countPredicates;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public Root<T> getEntityRoot() {
        return entityRoot;
    }

    public Root<T> getCountRoot() {
        return countRoot;
    }

    public List<Predicate> getEntityPredicates() {
        return entityPredicates;
    }

    public List<Predicate> getCountPredicates() {
        return countPredicates;
    }

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
