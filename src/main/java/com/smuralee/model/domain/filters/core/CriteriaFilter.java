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

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * <p>
 * Applying the predicates for criteria
 * </p>
 *
 * @param <T> - Entity where the criteria have to be applied
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
     * @return Predicate for the criteria type
     */
    Predicate apply(final CriteriaBuilder criteriaBuilder, final Root<T> root);
}
