package com.example.catologservice.service;

import com.example.catologservice.dto.CatalogDto;
import com.example.catologservice.jpa.CatalogEntity;
import com.example.catologservice.jpa.CatalogRepository;
import com.example.catologservice.vo.ResponseReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    CatalogRepository catalogRepository;
    Environment env;
    RestTemplate restTemplate;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository,  Environment env, RestTemplate restTemplate) {
        this.catalogRepository = catalogRepository;
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalog() {
        return catalogRepository.findAll();
    }

    @Override
    public CatalogDto getCatalogByCatalogId(String productId) {
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId);
        CatalogDto catalogDto = new ModelMapper().map(catalogEntity, CatalogDto.class);
        return catalogDto;
    }

    @Override
    public List<ResponseReview> getReviewByCatalogId(String productId) {
        String reviewUrl = String.format("http://localhost:8000/review-service/review/%s", productId);
        ResponseEntity<List<ResponseReview>> reviewListResponse =
                restTemplate.exchange(reviewUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ResponseReview>>() {
                        });
        List<ResponseReview> reviewList = reviewListResponse.getBody();
        return reviewList;
    }

    @Override
    public CatalogDto createCatalog(CatalogDto catalogDto) {
        ModelMapper mapper = new ModelMapper();
        // 설정 정보가 딱 맞아떨어져야지 변환 가능
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //DB에 저장하기 위해서는 UserEntity 가 필요
        CatalogEntity catalogEntity = mapper.map(catalogDto, CatalogEntity.class);
        catalogRepository.save(catalogEntity);
        return null;
    }

    @Override
    public CatalogDto deleteCatalog(CatalogDto catalogDto) {
        ModelMapper mapper = new ModelMapper();
        // 설정 정보가 딱 맞아떨어져야지 변환 가능
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //DB에 저장하기 위해서는 UserEntity 가 필요
        CatalogEntity catalogEntity = mapper.map(catalogDto, CatalogEntity.class);
        catalogRepository.delete(catalogEntity);
        return null;
    }
}

