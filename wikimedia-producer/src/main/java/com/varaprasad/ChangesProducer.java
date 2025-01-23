package com.varaprasad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChangesProducer {

    // logger to log messages
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    // single parameterized constructor - Spring automatically inject dependency
    public ChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(){
        String topic = "wikimedia_recent";

        // read real-time wikimedia stream data with event source

    }
}
