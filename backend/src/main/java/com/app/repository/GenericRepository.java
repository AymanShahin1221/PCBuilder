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

@Component
@Transactional
public class GenericRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public <T extends PCPart> List<T> getAll(Class<T> entityClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        return entityManager.createQuery(all).getResultList();
    }
}