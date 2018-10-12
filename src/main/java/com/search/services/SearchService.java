package com.search.services;

import com.search.model.view.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchService implements Search {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<SearchRequest> getSearchResults(SearchRequest searchRequest) {
        return null;
    }
}
