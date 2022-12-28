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

package com.smuralee.model.domain.filters;

import com.smuralee.model.domain.filters.core.LikeCriteriaFilter;
import com.smuralee.model.view.SearchRequest;
import com.smuralee.model.view.ViewModel;
import com.smuralee.persistence.entities.Product;
import com.smuralee.util.StringConstants;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;

public class ProductNameFilter extends LikeCriteriaFilter<Product> {

    private final ViewModel request;

    public ProductNameFilter(ViewModel request) {
        this.request = request;
    }

    @Override
    protected String getMatchPattern() {
        final String productName = ((SearchRequest) this.request).productName();
        return StringConstants.LIKE_PREFIX + productName.toLowerCase() +
                StringConstants.LIKE_PREFIX;
    }

    @Override
    protected Expression<String> getLikeAttribute(Root<Product> root) {
        return root.get("productName");
    }
}
