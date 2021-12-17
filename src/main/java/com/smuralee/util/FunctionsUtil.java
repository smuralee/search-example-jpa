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

package com.smuralee.util;

import com.smuralee.model.domain.PostalAddress;
import com.smuralee.model.domain.ProductItem;
import com.smuralee.model.domain.ProductManufacturer;
import com.smuralee.persistence.entities.Manufacturer;
import com.smuralee.persistence.entities.Product;

import java.util.function.Function;

public final class FunctionsUtil {

    /**
     * <p>
     * Extract {@link PostalAddress} from {@link Manufacturer}
     * </p>
     */
    private static final Function<Manufacturer, PostalAddress> getPostalAddress = manufacturer -> new PostalAddress(
            manufacturer.getAddressLine1(),
            manufacturer.getAddressLine2(),
            manufacturer.getCity(),
            manufacturer.getCountry(),
            manufacturer.getZipCode()
    );

    /**
     * <p>
     * Convert the {@link Manufacturer} to {@link ProductManufacturer} object
     * </p>
     */
    private static final Function<Manufacturer, ProductManufacturer> getProductManufacturer = manufacturer -> new ProductManufacturer(
            manufacturer.getName(),
            manufacturer.getDunsIdentifier(),
            getPostalAddress.apply(manufacturer)
    );

    /**
     * <p>
     * Convert the {@link Product} to {@link ProductItem} object
     * </p>
     */
    public static Function<Product, ProductItem> getProductItem = product ->
            new ProductItem(
                    product.getProductName(),
                    product.getProductSkuCode(),
                    getProductManufacturer.apply(product.getManufacturer())
            );

    private FunctionsUtil() {
    }

}
