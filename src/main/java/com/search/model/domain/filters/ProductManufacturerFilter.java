package com.search.model.domain.filters;

import com.search.model.domain.filters.core.LikeCriteriaFilter;
import com.search.model.view.SearchRequest;
import com.search.model.view.ViewModel;
import com.search.persistence.entities.Product;
import com.search.util.StringConstants;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ProductManufacturerFilter extends LikeCriteriaFilter<Product> {

    private ViewModel request;

    @Override
    protected String getMatchPattern() {
        final String productManufacturer = ((SearchRequest) this.request).getProductManufacturer();
        StringBuilder stringBuilder = new StringBuilder(StringConstants.LIKE_PREFIX);
        stringBuilder.append(productManufacturer);
        stringBuilder.append(StringConstants.LIKE_PREFIX);
        return stringBuilder.toString();
    }

    @Override
    protected Expression<String> getLikeAttribute(Root<Product> root) {
        return root.get(StringConstants.PRODUCT_MANUFACTURER);
    }
}
