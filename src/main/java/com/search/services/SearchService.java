package com.search.services;

import com.search.model.domain.filters.ProductIdFilter;
import com.search.model.domain.filters.core.InCriteriaFilter;
import com.search.model.view.SearchRequest;
import com.search.model.view.SearchResponse;
import com.search.persistence.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SearchService implements Search {

    @Autowired
    private EntityManager entityManager;

    @Override
    public SearchResponse getSearchResults(SearchRequest searchRequest) {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        final Root<Product> root = criteriaQuery.from(Product.class);
        InCriteriaFilter<Product> idFilter = new ProductIdFilter();
        criteriaQuery.select(root).where(idFilter.apply(criteriaBuilder, root));

        final TypedQuery<Product> query = this.entityManager.createQuery(criteriaQuery);
        final List<Product> resultList = query.getResultList();
        final SearchResponse response = new SearchResponse(resultList);
        return response;
    }

}
