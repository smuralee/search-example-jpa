package com.search.services;

import com.search.model.domain.filters.chain.ProductFilterChain;
import com.search.model.domain.filters.core.PredicateBuilder;
import com.search.model.view.SearchRequest;
import com.search.model.view.SearchResponse;
import com.search.persistence.entities.Product;
import com.search.persistence.entities.SupplyChain;
import com.search.util.PrimitiveConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

        //FIXME: Apply the design pattern to join of the tables
        final Join<Product, SupplyChain> supplyChainEntity = entityRoot.join("supplyChains");
        final Predicate entityRef = supplyChainEntity.get("id").in(searchRequest.getSupplyChainIds());

        final Join<Product, SupplyChain> supplyChainCount = countRoot.join("supplyChains");
        final Predicate countRef = supplyChainCount.get("id").in(searchRequest.getSupplyChainIds());

        predicateBuilder.getEntityPredicates().add(entityRef);
        predicateBuilder.getCountPredicates().add(countRef);

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
        entityQuery.setFirstResult((searchRequest.getPageNumber() - PrimitiveConstants.ONE) * searchRequest.getPageSize());
        entityQuery.setMaxResults(searchRequest.getPageSize());
        final List<Product> resultList = entityQuery.getResultList();
        return new SearchResponse(resultList, totalCount);
    }

}
