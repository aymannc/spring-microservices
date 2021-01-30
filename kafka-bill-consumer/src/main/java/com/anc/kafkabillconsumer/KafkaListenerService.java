package com.anc.kafkabillconsumer;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class KafkaListenerService {
    BillRepository billRepository;

    public KafkaListenerService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public static void createCSVFile(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                CSVWriter writer = new CSVWriter(new FileWriter(filePath));
                String[] header = {"id", "name", "total"};
                writer.writeNext(header);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendCSVFile(String filePath, Bill bill) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
            String[] record = {String.valueOf(bill.getId()), bill.getCustomerName(), String.valueOf(bill.getTotal())};
            writer.writeNext(record);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "FACTURATION", groupId = "group_id")
    public void onMessage(String message) {
        String filePath = "C:\\Users\\ayman\\my_projects\\JEE\\microservices\\kafka-bill-consumer\\src\\main\\resources\\data.csv";
        createCSVFile(filePath);

        Gson gson = new Gson();
        Bill bill = gson.fromJson(message, Bill.class);
        System.out.println("KafkaListenerService => " + bill);

        bill = billRepository.save(bill);
        appendCSVFile(filePath, bill);
    }
}
