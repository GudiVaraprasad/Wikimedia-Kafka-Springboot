package consumer.db;

import consumer.db.entity.WikimediaData;
import consumer.db.repository.WikimediaDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DBConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConsumer.class);

    private WikimediaDataRepo datarepo;

    public DBConsumer(WikimediaDataRepo datarepo) {
        this.datarepo = datarepo;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )

    public void consume(String eventMessage){
        LOGGER.info(String.format("Event message received -> %s", eventMessage));

        WikimediaData wikimediadata = new WikimediaData();
        wikimediadata.setWikiEventData(eventMessage);

        datarepo.save(wikimediadata);
    }
}
