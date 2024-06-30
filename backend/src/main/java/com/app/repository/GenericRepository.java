package com.app.repository;

import com.app.entity.model.PCPart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Generic Repository that fetches parts from database
 */

@Component
@Transactional
public class GenericRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public GenericRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retrieves all entities of a specified class from the database.
     * @param entityClass class of entities to retrieve
     * @return <T> list containing entities of specified class
     * @param <T> type of entity ---> must extend PCPart superclass
     */
    public <T extends PCPart> JSONArray getAllPartsByCategory(Class<T> entityClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        // SELECT * FROM entityClass (table)
        List<T> resultSet = entityManager.createQuery(all).getResultList();

        // convert List<T> to JsonArray
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData;
        try
        {
            jsonData = objectMapper.writeValueAsString(resultSet);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }

        // remove pid from resultset
        JSONArray result = new JSONArray(jsonData);
        for(int i = 0; i < result.length(); i++)
        {
            JSONObject jsonObject = result.getJSONObject(i);
            jsonObject.remove("pid");
        }

        return result;
    }
}

