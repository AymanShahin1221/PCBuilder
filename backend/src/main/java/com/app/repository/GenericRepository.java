package com.app.repository;

import com.app.entity.model.PCPart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.json.JSONArray;
import org.json.JSONObject;
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

    private <T> String listToString(List<T> list) {
        ObjectMapper objectMapper = new ObjectMapper();

        String listAsString;
        try
        {
            listAsString = objectMapper.writeValueAsString(list);
        }
        catch (JsonProcessingException e)
        {
            logger.error("Could not convert entity resultset list to String.");
            throw new RuntimeException(e);
        }

        return listAsString;
    }

    private  <T extends PCPart> int getTableSize(Class<T> entityClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(criteriaBuilder.count(root));

        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

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

        String jsonData = listToString(resultSet);
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
    public <T extends PCPart> JSONObject getPartsByCategoryPaginated(Class<T> entityClass, int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        // Apply pagination
        List<T> resultSet = entityManager
                .createQuery(all)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();


        String jsonData = listToString(resultSet);

        int tableSize = getTableSize(entityClass);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("products", new JSONArray(jsonData));
        jsonResponse.put("totalEntries", tableSize);

        return jsonResponse;
    }

    /**
     * Retrieves paginated entities which match a search term
     * @param entityClass class of entities to retrieve
     * @param page the page number to retrieve
     * @param size number of items per page
     * @return <T> JSONArray containing entities of specified class
     * @param <T> <T> type of entity ---> must extend PCPart superclass
     */
    public <T extends PCPart> JSONObject findProductsBySearchTerm(Class<T> entityClass, int page, int size, String searchTerm) {
        int offset = (page - 1) * size;

        String countResultsQuery;
        String getProductsQuery;
        if(entityClass.getSimpleName().equalsIgnoreCase("gpu"))
        {
            countResultsQuery = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE e.chipset ILIKE :searchTerm or e.name ILIKE :searchTerm";
            getProductsQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.chipset ILIKE :searchTerm or e.name ILIKE :searchTerm";
        }

        else
        {
            countResultsQuery = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE e.name ILIKE :searchTerm";
            getProductsQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.name ILIKE :searchTerm";
        }

        Query countQuery = entityManager.createQuery(countResultsQuery);
        countQuery.setParameter("searchTerm", "%" + searchTerm + "%");
        long totalEntries = (Long) countQuery.getSingleResult();

        Query queryProducts = entityManager.createQuery(getProductsQuery);
        queryProducts.setParameter("searchTerm", "%" + searchTerm + "%");
        queryProducts.setFirstResult(offset);
        queryProducts.setMaxResults(size);

        List<T> results = queryProducts.getResultList();
        String jsonData = listToString(results);

        JSONObject response = new JSONObject();
        response.put("totalEntries", totalEntries);
        response.put("products", new JSONArray(jsonData));

        return response;
    }
}