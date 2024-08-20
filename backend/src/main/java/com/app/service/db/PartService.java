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

    private void removeFields(JSONArray jsonArray, List<String> fieldsToRemove) {
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (String field : fieldsToRemove)
            {
                if (jsonObject.has(field))
                    jsonObject.remove(field);
            }
        }
    }

    public <T extends PCPart> JSONObject getPartsByCategoryPaginated(Class<T> entityClass, List<String> fieldsToFilter, int page, int size) {

        JSONObject jsonData = genericRepository.getPartsByCategoryPaginated(entityClass, page, size);
        JSONArray productsArray = jsonData.getJSONArray("products");

        if(!fieldsToFilter.isEmpty())
            removeFields(productsArray, fieldsToFilter);

        return jsonData;
    }

    public <T extends PCPart> JSONObject findProductsBySearchTerm(Class<T> entityClass, List<String> fieldsToFilter, int page, int size, String searchTerm) {
        JSONObject jsonData = genericRepository.findProductsBySearchTerm(entityClass, page, size, searchTerm);
        JSONArray productsArray = jsonData.getJSONArray("products");

        if(!fieldsToFilter.isEmpty())
            removeFields(productsArray, fieldsToFilter);

        return genericRepository.findProductsBySearchTerm(entityClass, page, size, searchTerm);
    }
}
