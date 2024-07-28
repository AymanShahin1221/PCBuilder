package com.app.service.db;

import com.app.entity.model.PCPart;
import com.app.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private final GenericRepository genericRepository;

    Logger logger = LoggerFactory.getLogger(PartService.class);

    @Autowired
    public PartService(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    public <T extends PCPart> JSONArray getAllPartsByCategory(Class<T> entityClass, List<String> fieldsToFilter) {
        JSONArray partsJsonArray = genericRepository.getAllPartsByCategory(entityClass);
        for(int i = 0; i < partsJsonArray.length(); i++)
        {
            JSONObject jsonObject = partsJsonArray.getJSONObject(i);
            for(String field : fieldsToFilter)
            {
                if (jsonObject.has(field))
                    jsonObject.remove(field);

                else
                    logger.warn("Field {} not found in JSON object", field);
            }
        }
        return partsJsonArray;
    }

    public <T extends PCPart> JSONArray getAllPartsByCategory(Class<T> entityClass) {
        return genericRepository.getAllPartsByCategory(entityClass);
    }
}
