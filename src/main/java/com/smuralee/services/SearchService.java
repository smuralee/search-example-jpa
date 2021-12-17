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

package com.smuralee.services;

import com.smuralee.model.domain.ProductItem;
import com.smuralee.model.domain.filters.chain.ProductFilterChain;
import com.smuralee.model.domain.filters.core.PredicateBuilder;
import com.smuralee.model.view.SearchRequest;
import com.smuralee.model.view.SearchResponse;
import com.smuralee.persistence.entities.Product;
import com.smuralee.util.FunctionsUtil;
import com.smuralee.util.PrimitiveConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService implements Search {

    private final EntityManager entityManager;

    public SearchService(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public SearchResponse getSearchResults(SearchRequest searchRequest) {

        // Get the criteria builder and create the root entity
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        // Entity query
        final CriteriaQuery<Product> entityCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        final Root<Product> entityRoot = entityCriteriaQuery.from(Product.class);

        // Count query
        final CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<Product> countRoot = countCriteriaQuery.from(Product.class);

        // Empty predicate list for applying the restrictions
        List<Predicate> entityPredicates = new ArrayList<>();
        List<Predicate> countPredicates = new ArrayList<>();

        // Create the builder for applying the predicates
        PredicateBuilder<Product> predicateBuilder = new PredicateBuilder<>();
        predicateBuilder
                .forSearchOf(searchRequest)
                .withCriteriaBuilder(criteriaBuilder)
                .havingEntityRootOf(entityRoot)
                .andEntityPredicateListOf(entityPredicates)
                .havingCountRootOf(countRoot)
                .andCountPredicateListOf(countPredicates);

        // Applying the predicates
        ProductFilterChain chain = new ProductFilterChain();
        chain.applyPredicates(predicateBuilder);

        // Fetch the predicates
        Predicate entityPredicate = criteriaBuilder.and(predicateBuilder.getEntityPredicates().toArray(new Predicate[PrimitiveConstants.ZERO]));
        Predicate countPredicate = criteriaBuilder.and(predicateBuilder.getCountPredicates().toArray(new Predicate[PrimitiveConstants.ZERO]));

        // Passing the predicates for entity query
        entityCriteriaQuery.select(entityRoot).where(entityPredicate);

        // Passing the predicates for count query
        countCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(countPredicate);

        // Query execution - get total count
        final TypedQuery<Long> countQuery = this.entityManager.createQuery(countCriteriaQuery);
        final Long totalCount = countQuery.getSingleResult();

        // Query execution - get products with pagination
        final TypedQuery<Product> entityQuery = this.entityManager.createQuery(entityCriteriaQuery);
        entityQuery.setFirstResult((searchRequest.pageNumber() - PrimitiveConstants.ONE) * searchRequest.pageSize());
        entityQuery.setMaxResults(searchRequest.pageSize());
        final List<Product> resultList = entityQuery.getResultList();

        // Converting to the domain object
        final List<ProductItem> productItems = resultList.stream()
                .map(FunctionsUtil.getProductItem)
                .collect(Collectors.toList());
        return new SearchResponse(productItems, totalCount);
    }

}
