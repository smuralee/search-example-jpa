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
