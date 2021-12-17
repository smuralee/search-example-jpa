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

import com.smuralee.util.StringConstants;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.Assert;

/**
 * <p>
 * Criteria filter to implement the in-clause for the query
 * </p>
 *
 * @param <T> - Entity where the in-clause has to be applied
 */
public abstract class InCriteriaFilter<T> implements CriteriaFilter<T> {

    @Override
    public Predicate apply(CriteriaBuilder criteriaBuilder, Root<T> root) {
        return this.getInAttribute(root);
    }

    /**
     * <p>
     * Returns the predicate to be applied for the in-clause
     * </p>
     *
     * @param root - Entity root
     * @return - The predicate for the in-clause
     */
    protected Predicate getInAttribute(Root<T> root) {
        Assert.notNull(root, StringConstants.ROOT_CANNOT_BE_NULL);
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }

}
