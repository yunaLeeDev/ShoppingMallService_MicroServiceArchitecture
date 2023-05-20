package com.example.catologservice.service;

import com.example.catologservice.dto.CatalogDto;
import com.example.catologservice.jpa.CatalogEntity;
import com.example.catologservice.vo.ResponseReview;

import java.util.List;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalog();

    CatalogDto createCatalog(CatalogDto catalogDto);
    CatalogDto deleteCatalog(CatalogDto catalogDto);
    CatalogDto getCatalogByCatalogId(String catalogId);

    List<ResponseReview> getReviewByCatalogId(String catalogId);
}
