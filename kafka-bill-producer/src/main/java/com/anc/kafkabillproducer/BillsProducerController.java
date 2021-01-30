package com.anc.kafkabillproducer;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillsProducerController {
    private static final String TOPIC = "FACTURATION";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BillsProducerController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("sendBill/")
    private Bill sendBill(@RequestBody Bill bill) {
        kafkaTemplate.send(TOPIC, bill);
        return bill;
    }

    @GetMapping("generateBill/{id}/{name}/{total}")
    private Bill generateBill(@PathVariable long id, @PathVariable String name, @PathVariable float total) {
        Bill bill = new Bill(id, name, total);
        kafkaTemplate.send(TOPIC, bill);
        return bill;
    }
}
