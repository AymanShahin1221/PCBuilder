package com.app.service.db;

import com.app.entity.model.PCPart;
import com.app.repository.*;
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

    public <T extends PCPart> List<T> getAllParts(Class<T> tClass) {
        return genericRepository.getAll(tClass);
    }
}
