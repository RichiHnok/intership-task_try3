package com.richi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.richi.entity.Doc;

public interface DocRepository extends MongoRepository<Doc, String> {
    
}
