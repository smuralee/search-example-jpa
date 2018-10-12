package com.search.services;

import com.search.model.view.SearchRequest;
import com.search.model.view.SearchResponse;

import java.util.List;

public interface Search {

    /**
     * <p>
     * Returns the search results based on the search criteria
     * </p>
     *
     * @param searchRequest - Search criteria provided by the user
     * @return List<SearchResponse> - Search results for the criteria
     */
    List<SearchResponse> getSearchResults(final SearchRequest searchRequest);
}
