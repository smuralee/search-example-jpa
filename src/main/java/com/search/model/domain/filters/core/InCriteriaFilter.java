package com.search.model.domain.filters.core;

import com.search.util.StringConstants;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class InCriteriaFilter<T> implements CriteriaFilter<T> {

    @Override
    public Predicate apply(CriteriaBuilder criteriaBuilder, Root<T> root) {
        return this.getInAttribute(root);
    }

    protected Predicate getInAttribute(Root<T> root) {
        Assert.notNull(root, StringConstants.ROOT_CANNOT_BE_NULL);
        throw new UnsupportedOperationException(StringConstants.SUBCLASS_MUST_IMPLEMENT_METHOD);
    }

}
