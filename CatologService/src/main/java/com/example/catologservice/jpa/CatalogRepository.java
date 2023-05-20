package com.example.catologservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {
    // Product ID로 물건 검색
    CatalogEntity findByProductId(String productId);
}
