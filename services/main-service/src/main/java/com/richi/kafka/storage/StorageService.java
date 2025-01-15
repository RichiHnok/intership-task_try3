package com.richi.kafka.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.richi.dto.DocumentToStoreRequest;

@Service
public class StorageService {

    private static final Logger log = LoggerFactory.getLogger(StorageService.class);
    
    @Autowired private KafkaTemplate<String, String> kafkaTemplate;

    public void sendDocumentToStorage(DocumentToStoreRequest request){
        log.info("Sending notification with body <{}>", request.content());

        Message<String> message = MessageBuilder
            .withPayload(request.content())
            .setHeader(KafkaHeaders.TOPIC, "store-topic")
            .build();
        kafkaTemplate.send(message);
    }
}
