package com.varaprasad;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class ChangesProducer {

    // logger to log messages
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    // single parameterized constructor - Spring automatically inject dependency
    public ChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recent";

        // read real-time wikimedia stream data with event source
        EventHandler eventHandler = new ChangesHandler(kafkaTemplate, topic);

        // Wikimedia json real-time stream data
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        // pass URL to event source and connect to wikimedia source to read data
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();

        // internally works as threads - send each event to kafka topic
        eventSource.start(); // call thread

        TimeUnit.MINUTES.sleep(10); // sleep thread

    }
}
