package com.richi.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.richi.entity.Doc;
import com.richi.repository.DocRepository;

@Service
public class MessageConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired private DocRepository repository;

    @KafkaListener(topics = "store-topic")
    public void consumeStoreTopic(
        DocumentToStore documentToStore
    ) throws MessagingException{
        log.info(
            String.format("Consuming the message from store-topic Topic:: %s", documentToStore)
        );

        repository.save(new Doc(documentToStore.content()));
    }
}
