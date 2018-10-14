package com.search.services;

import com.search.model.domain.filters.ProductDescriptionFilter;
import com.search.model.domain.filters.ProductIdFilter;
import com.search.model.domain.filters.ProductManufacturerFilter;
import com.search.model.view.SearchRequest;
import com.search.model.view.SearchResponse;
import com.search.persistence.entities.Product;
import com.search.util.PrimitiveConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService implements Search {

    @Autowired
    private EntityManager entityManager;

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

        // Get all the predicates for applying the restrictions
        List<Predicate> entityPredicates = new ArrayList<>();
        List<Predicate> countPredicates = new ArrayList<>();

        if (null != searchRequest.getProductId()) {
            entityPredicates.add(new ProductIdFilter(searchRequest).apply(criteriaBuilder, entityRoot));
            countPredicates.add(new ProductIdFilter(searchRequest).apply(criteriaBuilder, countRoot));
        }
        if (null != searchRequest.getProductDescription()) {
            entityPredicates.add(new ProductDescriptionFilter(searchRequest).apply(criteriaBuilder, entityRoot));
            countPredicates.add(new ProductDescriptionFilter(searchRequest).apply(criteriaBuilder, countRoot));
        }
        if (null != searchRequest.getProductManufacturer()) {
            entityPredicates.add(new ProductManufacturerFilter(searchRequest).apply(criteriaBuilder, entityRoot));
            countPredicates.add(new ProductManufacturerFilter(searchRequest).apply(criteriaBuilder, countRoot));
        }

        Predicate entityPredicate = criteriaBuilder.and(entityPredicates.toArray(new Predicate[PrimitiveConstants.ZERO]));
        Predicate countPredicate = criteriaBuilder.and(countPredicates.toArray(new Predicate[PrimitiveConstants.ZERO]));

        // Passing the predicates for entity query
        entityCriteriaQuery.select(entityRoot).where(entityPredicate);

        // Passing the predicates for count query
        countCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(countPredicate);

        // Query execution - get total count
        final TypedQuery<Long> countQuery = this.entityManager.createQuery(countCriteriaQuery);
        final Long totalCount = countQuery.getSingleResult();

        // Query execution - get products with pagination
        final TypedQuery<Product> entityQuery = this.entityManager.createQuery(entityCriteriaQuery);
        entityQuery.setFirstResult((searchRequest.getPageNumber() - PrimitiveConstants.ONE) * searchRequest.getPageSize());
        entityQuery.setMaxResults(searchRequest.getPageSize());
        final List<Product> resultList = entityQuery.getResultList();
        return new SearchResponse(resultList, totalCount);
    }

}
