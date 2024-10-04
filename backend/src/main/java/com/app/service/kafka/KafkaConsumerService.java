package com.app.service.kafka;

import com.app.entity.model.*;
import com.app.entity.model.PCPart;
import com.app.service.db.DBUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class KafkaConsumerService
{

    private final DBUpdateService dbUpdateService;

    @Autowired
    public KafkaConsumerService(DBUpdateService dbUpdateService)
    {
        this.dbUpdateService = dbUpdateService;
    }

    @KafkaListener(
            topics = {
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
                    "storagePartsTopic"
            },
            groupId = "consumerGroup1"
    )
    public void topicListener(PCPart pcPart) throws SQLException
    {
        ((Upsertable) pcPart).insertPart(dbUpdateService);
    }
}
