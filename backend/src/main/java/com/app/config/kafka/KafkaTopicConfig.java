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
            "casePartsTopic",
            "coolerPartsTopic",
            "cpuPartsTopic",
            "gpuPartsTopic",
            "keyboardPartsTopic",
            "memoryPartsTopic",
            "monitorPartsTopic",
            "motherboardPartsTopic",
            "osPartsTopic",
            "powerSupplyPartsTopic",
            "storagePartsTopic",
            "testTopic"
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
