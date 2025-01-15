package com.richi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richi.dto.DocumentToStoreRequest;
import com.richi.kafka.storage.StorageService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/save-doc")
@Tag(name = "Сохранение строки в mongo")
public class StorageController {

    @Autowired private StorageService storageService;
    
    @PostMapping
    public ResponseEntity<Void> storeDocument(
        @RequestBody @Valid DocumentToStoreRequest request
    ){
        storageService.sendDocumentToStorage(request);
        return ResponseEntity.accepted().build();
    }
}
