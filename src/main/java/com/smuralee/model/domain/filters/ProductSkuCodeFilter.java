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


import com.smuralee.model.domain.filters.core.EqualCriteriaFilter;
import com.smuralee.model.view.SearchRequest;
import com.smuralee.model.view.ViewModel;
import com.smuralee.persistence.entities.Product;
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
        return root.get("productSkuCode");
    }
}
