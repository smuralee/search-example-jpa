package com.smuralee.model.domain.filters;


import com.smuralee.model.domain.filters.core.EqualCriteriaFilter;
import com.smuralee.model.view.SearchRequest;
import com.smuralee.model.view.ViewModel;
import com.smuralee.persistence.entities.Product;
import com.smuralee.util.StringConstants;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;


public class ProductSkuCodeFilter extends EqualCriteriaFilter<Product, String> {

    private final ViewModel request;

    public ProductSkuCodeFilter(ViewModel request) {
        this.request = request;
    }

    @Override
    protected String getEqualParameter() {
        return ((SearchRequest) this.request).productSkuCode();
    }

    @Override
    protected Expression<String> getEqualityAttribute(Root<Product> root) {
        return root.get(StringConstants.PRODUCT_SKU_CODE);
    }
}
