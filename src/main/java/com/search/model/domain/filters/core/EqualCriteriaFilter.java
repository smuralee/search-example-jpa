package com.search.model.domain.filters.core;

import com.search.util.StringConstants;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
     * @param root
     * @return - Expression of the attribute to be compared from the {@link Root}
     */
    protected Expression<R> getEqualityAttribute(Root<T> root) {
        Assert.notNull(root, StringConstants.ROOT_CANNOT_BE_NULL);
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }
}
