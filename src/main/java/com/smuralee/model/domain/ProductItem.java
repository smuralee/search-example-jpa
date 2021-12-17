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

package com.smuralee.model.domain;

import java.util.Objects;

public class ProductItem {

    private String name;
    private String skuCode;
    private ProductManufacturer productManufacturer;

    public ProductItem(String name, String skuCode, ProductManufacturer productManufacturer) {
        this.name = name;
        this.skuCode = skuCode;
        this.productManufacturer = productManufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public ProductManufacturer getManufacturer() {
        return productManufacturer;
    }

    public void setManufacturer(ProductManufacturer productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem productItem = (ProductItem) o;
        return getSkuCode().equals(productItem.getSkuCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSkuCode());
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "name='" + name + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", manufacturer=" + productManufacturer +
                '}';
    }
}
