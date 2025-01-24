package consumer.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DBConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConsumer.class);

    @KafkaListener(
            topics = "wikimedia_recent",
            groupId = "consumerGroup"
    )

    public void consume(String eventMessage){
        LOGGER.info(String.format("Event message received -> %s", eventMessage));
    }
}
