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

    public <T extends PCPart> JSONObject getPartsByCategoryPaginated(Class<T> entityClass, List<String> fieldsToFilter, int page, int size) {

        JSONObject jsonData = genericRepository.getPartsByCategoryPaginated(entityClass, page, size);
        JSONArray productsArray = jsonData.getJSONArray("products");

        for (int i = 0; i < productsArray.length(); i++)
        {
            JSONObject jsonObject = productsArray.getJSONObject(i);
            for (String field : fieldsToFilter)
            {
                if (jsonObject.has(field))
                    jsonObject.remove(field);
            }
        }
        return jsonData;
    }
}
