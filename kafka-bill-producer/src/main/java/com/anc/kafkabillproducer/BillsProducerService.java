package com.anc.kafkabillproducer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class BillsProducerService {
    private static final String TOPIC = "FACTURATION";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String[] names = {"ayman", "ahmed", "souad", "fatima"};

    @Autowired
    public BillsProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Bean
    public void sendBill() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            long id = new Random().nextInt(10000);
            int stringIndex = new Random().nextInt(names.length);
            float amount = new Random().nextFloat() * 100000;
            kafkaTemplate.send(TOPIC, new Bill(id, names[stringIndex], amount));
        }, 0, 100, TimeUnit.MILLISECONDS);
    }
}
