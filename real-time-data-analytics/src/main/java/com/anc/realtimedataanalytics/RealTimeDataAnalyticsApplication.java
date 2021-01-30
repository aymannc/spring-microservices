package com.anc.realtimedataanalytics;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Properties;

@SpringBootApplication
public class RealTimeDataAnalyticsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeDataAnalyticsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "bills-stream");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> floatSerde = Serdes.Long();
//        Gson gson = new Gson();
//
//        KStream<String, Long> billMainStream =
//                streamsBuilder
//                        .stream("FACTURATION", Consumed.with(stringSerde, stringSerde))
//                        .map((k, v) -> new KeyValue<>(k, gson.fromJson(v, Bill.class)))
//                        .map((k, v) -> new KeyValue<>(v.getCustomerName().toLowerCase(), v.getTotal()))
//                        .groupBy((k, v) -> k)
//                        .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
//                        .count(Materialized.as("countBill"))
//                        .toStream()
//                        .map((k, v) -> KeyValue.pair(k.key(), v));
//        billMainStream.to("RealTimeDataAnalyticsTopic", Produced.with(stringSerde,floatSerde));


        KStream<String, Long> billMainStream =
                streamsBuilder
                        .stream("FACTURATION", Consumed.with(stringSerde, stringSerde))
                        .map((k, v) -> KeyValue.pair("total", 1))
                        .groupByKey()
                        .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                        .count(Materialized.as("countBill"))
                        .toStream()
                        .map((k, v) -> KeyValue.pair(k.key(), v));
        billMainStream.to("RealTimeDataAnalyticsTopic", Produced.with(Serdes.String(), Serdes.Long()));

        Topology topology = streamsBuilder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        kafkaStreams.start();

    }
}
