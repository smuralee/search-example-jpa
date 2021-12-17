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
 * Filter for applying the like criteria
 * </p>
 *
 * @param <T> - Entity type where like criteria is applied
 */
public abstract class LikeCriteriaFilter<T> implements CriteriaFilter<T> {

    @Override
    public Predicate apply(CriteriaBuilder criteriaBuilder, Root<T> root) {
        return criteriaBuilder.like(criteriaBuilder.lower(this.getLikeAttribute(root)), this.getMatchPattern());
    }

    /**
     * <p>
     * Match pattern for the like query
     * </p>
     *
     * @return - wild card pattern
     */
    protected String getMatchPattern() {
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }

    /**
     * <p>
     * Expression for the like query on the entity attribute
     * </p>
     *
     * @param root - Entity root
     * @return - The expression for the like query
     */
    protected Expression<String> getLikeAttribute(Root<T> root) {
        Assert.notNull(root, StringConstants.ROOT_CANNOT_BE_NULL);
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }
}
