package com.app.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class KafkaTopicConfig {

    private static final String[] TOPIC_NAMES = {
            "case_parts_topic",
            "cooler_parts_topic",
            "cpu_parts_topic",
            "gpu_parts_topic",
            "keyboard_parts_topic",
            "memory_parts_topic",
            "monitor_parts_topic",
            "motherboard_parts_topic",
            "os_parts_topic",
            "power_supply_parts_topic",
            "storage_parts_topic"
    };

    @Bean
    public List<NewTopic> createTopics() {
        List<NewTopic> topics = new ArrayList<>();
        for (String topicName : TOPIC_NAMES)
        {
            topics.add(
                    TopicBuilder
                    .name(topicName)
                    .build()
            );
        }
        return topics;
    }
}
