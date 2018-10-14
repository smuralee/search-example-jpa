package com.search.model.domain.filters.core;

import com.search.util.StringConstants;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
