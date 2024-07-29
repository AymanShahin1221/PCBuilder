package com.app.repository;

import com.app.entity.model.PCPart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(GenericRepository.class);


    /**
     * Retrieves non-paginated entities of a specified class
     * @param entityClass class of entities to retrieve
     * @return JSONArray containing entities of specified class
     * @param <T> type of entity ---> must extend PCPart superclass
     */
    public <T extends PCPart> JSONArray getAllPartsByCategory(Class<T> entityClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        // SELECT * FROM entityClass (table)
        List<T> resultSet = entityManager.createQuery(all).getResultList();

        // convert List<T> to String
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData;
        try
        {
            jsonData = objectMapper.writeValueAsString(resultSet);
        }
        catch (JsonProcessingException e)
        {
            logger.error("Could not convert entity resultset list to String.");
            throw new RuntimeException(e);
        }

        return new JSONArray(jsonData);
    }

    /**
     * Retrieves paginated entities of a specified class
     * @param entityClass class of entities to retrieve
     * @param page the page number to retrieve
     * @param size number of items per page
     * @return <T> JSONArray containing entities of specified class
     * @param <T> type of entity ---> must extend PCPart superclass
     */
    public <T extends PCPart> JSONArray getPartsByCategoryPaginated(Class<T> entityClass, int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        // Apply pagination
        List<T> resultSet = entityManager
                .createQuery(all)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        // convert List<T> to String
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData;
        try
        {
            jsonData = objectMapper.writeValueAsString(resultSet);
        }
        catch (JsonProcessingException e)
        {
            logger.error("Could not convert entity resultset list to String. (Pagination)");
            throw new RuntimeException(e);
        }

        return new JSONArray(jsonData);
    }
}
