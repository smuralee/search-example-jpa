package com.search.api;

import com.search.model.view.SearchRequest;
import com.search.model.view.SearchResponse;
import com.search.persistence.entities.Product;
import com.search.persistence.repositories.ProductRepository;
import com.search.services.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Search search;

    /**
     * <p>
     * Returns all the products
     * </p>
     *
     * @return the list of products
     */
    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    @GetMapping("/find")
    public SearchResponse testSearch(){
        return this.searchByCriteria(null);
    }

    /**
     * <p>
     *     Returns the search result as per the search criteria
     * </p>
     * @param searchRequest - search criteria from user
     * @return the search results
     */
    @PostMapping("/search")
    public SearchResponse searchByCriteria(@RequestBody SearchRequest searchRequest){
        return this.search.getSearchResults(searchRequest);
    }
}
