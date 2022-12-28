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

package com.smuralee.model.domain.filters.chain;

import com.smuralee.model.domain.filters.core.PredicateBuilder;
import com.smuralee.model.domain.filters.core.SearchCriteriaChain;
import com.smuralee.persistence.entities.Product;

/**
 * <p>
 * Configures the sequence of steps in the filter chain
 * </p>
 */
public class ProductFilterChain {

    private final SearchCriteriaChain<Product> productSkuCodeProcessor = new ProductSkuCodeProcessor();

    public ProductFilterChain() {

        // Initialize the stages
        SearchCriteriaChain<Product> productNameProcessor = new ProductNameProcessor();
        SearchCriteriaChain<Product> productManufacturerProcessor = new ProductManufacturerProcessor();

        // Setting the filter chaining
        productSkuCodeProcessor.setNextChain(productNameProcessor);
        productNameProcessor.setNextChain(productManufacturerProcessor);
    }

    /**
     * <p>
     * Initializes the predicate chain
     * </p>
     *
     * @param builder - Predicate builder
     */
    public void applyPredicates(PredicateBuilder<Product> builder) {
        this.productSkuCodeProcessor.apply(builder);
    }


}
