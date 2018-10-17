package com.search.api;

import com.search.model.view.SearchRequest;
import com.search.model.view.SearchResponse;
import com.search.services.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ItemController {

    @Autowired
    private Search search;

    /**
     * <p>
     * Returns the search result as per the search criteria
     * </p>
     *
     * @param searchRequest - search criteria from user
     * @return the search results for the criteria
     */
    @PostMapping("/search")
    public SearchResponse searchByCriteria(@Valid @RequestBody SearchRequest searchRequest) {
        return this.search.getSearchResults(searchRequest);
    }
}
