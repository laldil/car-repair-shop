package com.example.car_repair_shop.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    public static final String ORDER_STATUS_COMMANDS_TOPIC = "order-status-commands";

    @Bean
    public NewTopic orderStatusCommandsTopic() {
        return TopicBuilder.name(ORDER_STATUS_COMMANDS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
