package com.example.catologservice.controller;

import com.example.catologservice.dto.CatalogDto;
import com.example.catologservice.jpa.CatalogEntity;
import com.example.catologservice.service.CatalogService;
import com.example.catologservice.vo.RequestCatalog;
import com.example.catologservice.vo.ResponseCatalog;
import com.example.catologservice.vo.ResponseReview;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private Environment env;
    CatalogService catalogService;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in Catalog service on Port %s", request.getServerPort());
    }

    @PostMapping("/catalog/add")
    public ResponseEntity<ResponseCatalog> createCatalogs(@RequestBody RequestCatalog catalog) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CatalogDto catalogDto = mapper.map(catalog, CatalogDto.class);
        catalogService.createCatalog(catalogDto);
        ResponseCatalog returnValue = mapper.map(catalogDto, ResponseCatalog.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/catalog/{productId}")
    public ResponseEntity<ResponseCatalog> getCatalogByCatalogId(@PathVariable("productId") String productId) {
        CatalogDto catalogDto = catalogService.getCatalogByCatalogId(productId);
        ResponseCatalog result = new ModelMapper().map(catalogDto, ResponseCatalog.class);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/catalog/{productId}/review", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ResponseReview>> getReviewByCatalogId(@PathVariable("productId") String productId) {
        List<ResponseReview> result = catalogService.getReviewByCatalogId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/catalog/all")
    public ResponseEntity<List<ResponseCatalog>> getAllCatalogs() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalog();
        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/catalog/delete/{productId}")
    public String deleteCatalogs(@PathVariable("productId") String productId) {
        CatalogDto catalogDto = catalogService.getCatalogByCatalogId(productId);
        catalogService.deleteCatalog(catalogDto);
        return "Delete catalog method is called";
    }
}