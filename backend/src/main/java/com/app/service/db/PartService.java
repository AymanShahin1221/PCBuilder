package com.app.service.db;

import com.app.entity.model.PCPart;
import com.app.repository.GenericRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private final GenericRepository genericRepository;

    @Autowired
    public PartService(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    public <T extends PCPart> JSONArray getAllPartsByCategory(Class<T> entityClass) {
        return genericRepository.getAllPartsByCategory(entityClass);
    }

    public <T extends PCPart> JSONArray getPartsByCategoryPaginated(Class<T> entityClass, List<String> fieldsToFilter, int page, int size) {
        JSONArray partsJsonArray = genericRepository.getPartsByCategoryPaginated(entityClass, page, size);
        for (int i = 0; i < partsJsonArray.length(); i++)
        {
            JSONObject jsonObject = partsJsonArray.getJSONObject(i);
            for (String field : fieldsToFilter)
            {
                if (jsonObject.has(field))
                    jsonObject.remove(field);
            }
        }
        return partsJsonArray;
    }
}
