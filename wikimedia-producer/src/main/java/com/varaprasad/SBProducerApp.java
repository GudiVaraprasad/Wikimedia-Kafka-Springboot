package com.varaprasad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SBProducerApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SBProducerApp.class);
    }

    private final ChangesProducer changesProducer;

    // Use constructor injection
    @Autowired
    public SBProducerApp(ChangesProducer changesProducer) {
        this.changesProducer = changesProducer;
    }

    @Override
    public void run(String... args) throws Exception {
        changesProducer.sendMessage();
    }
}
