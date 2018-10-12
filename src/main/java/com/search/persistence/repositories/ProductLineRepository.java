package com.search.persistence.repositories;

import com.search.persistence.entities.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Integer> {
}
