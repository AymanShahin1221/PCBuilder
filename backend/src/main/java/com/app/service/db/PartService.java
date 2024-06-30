package com.app.service.db;

import com.app.entity.model.PCPart;
import com.app.repository.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
