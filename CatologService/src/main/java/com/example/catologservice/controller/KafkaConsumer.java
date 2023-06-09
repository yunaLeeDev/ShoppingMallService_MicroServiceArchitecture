package com.example.catologservice.controller;

import com.example.catologservice.jpa.CatalogEntity;
import com.example.catologservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    CatalogRepository repository;

    @Autowired
    public KafkaConsumer(CatalogRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics="mju-order-topic")
    public void processMessage(String kafkaMessage){
        log.info("kafka message ===>"+kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        CatalogEntity entity = repository.findByProductId((String)map.get("productId"));
        entity.setStock(entity.getStock() - (Integer)map.get("qty"));

        repository.save(entity);
    }
}
