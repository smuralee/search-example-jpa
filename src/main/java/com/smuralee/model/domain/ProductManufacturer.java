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

public class ProductManufacturer {

    private String name;
    private String dunsIdentifier;
    private PostalAddress postalAddress;

    public ProductManufacturer(String name, String dunsIdentifier, PostalAddress postalAddress) {
        this.name = name;
        this.dunsIdentifier = dunsIdentifier;
        this.postalAddress = postalAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDunsIdentifier() {
        return dunsIdentifier;
    }

    public void setDunsIdentifier(String dunsIdentifier) {
        this.dunsIdentifier = dunsIdentifier;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductManufacturer that = (ProductManufacturer) o;
        return getDunsIdentifier().equals(that.getDunsIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDunsIdentifier());
    }

    @Override
    public String toString() {
        return "ProductManufacturer{" +
                "name='" + name + '\'' +
                ", dunsIdentifier='" + dunsIdentifier + '\'' +
                ", postalAddress=" + postalAddress +
                '}';
    }
}
