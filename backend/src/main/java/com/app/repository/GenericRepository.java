package com.app.repository;

import com.app.entity.model.PCPart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
    private EntityManager entityManager;

    /**
     * Retrieves all entities of a specified class from the database.
     * @param entityClass class of entities to retrieve
     * @return <T> list containing entities of specified class
     * @param <T> type of entity - must extend PCPart superclass
     */
    public <T extends PCPart> List<T> getAll(Class<T> entityClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        return entityManager.createQuery(all).getResultList();
    }
}