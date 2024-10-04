package com.app.service.kafka;

import com.app.entity.model.PCPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Producer class responsible for sending messages to topics
 * Configured to send messages with a string key and PCPart as the value
 */
@Service
public class KafkaProducerService
{

    private final KafkaTemplate<String, PCPart> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, PCPart> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * @param topic  name of the Kafka topic to send message to
     * @param pcPart object to be sent
     *               --
     *               Relevant topics
     *               --
     *               casePartsTopic
     *               coolerPartsTopic
     *               cpuPartsTopic
     *               gpuPartsTopic
     *               keyboardPartsTopic
     *               memoryPartsTopic
     *               monitorPartsTopic
     *               motherboardPartsTopic
     *               osPartsTopic
     *               powerSupplyPartsTopic
     *               storagePartsTopic
     */
    public void send(String topic, PCPart pcPart)
    {
        kafkaTemplate.send(topic, pcPart);
    }
}
