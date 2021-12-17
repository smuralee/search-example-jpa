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
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.Assert;

/**
 * <p>
 * Abstract class for applying the equality criteria for the entity type
 * </p>
 *
 * @param <T> - Entity type
 * @param <R> - Attribute type to be compared in the criteria
 */
public abstract class EqualCriteriaFilter<T, R> implements CriteriaFilter<T> {

    @Override
    public Predicate apply(CriteriaBuilder criteriaBuilder, Root<T> root) {
        return criteriaBuilder.equal(this.getEqualityAttribute(root), this.getEqualParameter());
    }

    /**
     * <p>
     * Returns the equality parameter from the incoming request to be compared with
     * </p>
     *
     * @return - The equality parameter from the incoming request
     */
    protected R getEqualParameter() {
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }

    /**
     * <p>
     * Returns the expression to be compared with
     * </p>
     *
     * @param root - Root entity
     * @return - Expression of the attribute to be compared from the {@link Root}
     */
    protected Expression<R> getEqualityAttribute(Root<T> root) {
        Assert.notNull(root, StringConstants.ROOT_CANNOT_BE_NULL);
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }
}
