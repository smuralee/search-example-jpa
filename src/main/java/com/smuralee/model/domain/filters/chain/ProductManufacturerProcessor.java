/*
 * Copyright 2022 Suraj Muraleedharan
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.smuralee.model.domain.filters.chain;

import com.smuralee.model.domain.filters.ProductManufacturerFilter;
import com.smuralee.model.domain.filters.core.PredicateBuilder;
import com.smuralee.model.domain.filters.core.SearchCriteriaChain;
import com.smuralee.model.view.SearchRequest;
import com.smuralee.persistence.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;

/**
 * <p>
 * Applies the item brand filter
 * </p>
 */
public class ProductManufacturerProcessor extends SearchCriteriaChain<Product> {

    @Override
    public void applyPredicate(PredicateBuilder<Product> builder) {
        final SearchRequest searchRequest = (SearchRequest) builder.getViewModel();
        final CriteriaBuilder criteriaBuilder = builder.getCriteriaBuilder();
        if (null != searchRequest.productManufacturer()) {
            builder.addEntityPredicate((new ProductManufacturerFilter(searchRequest).apply(criteriaBuilder, builder.getEntityRoot())));
            builder.addCountPredicate(new ProductManufacturerFilter(searchRequest).apply(criteriaBuilder, builder.getCountRoot()));
        }
    }
}
