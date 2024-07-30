package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Implementation of the DomainCategoryRepositoryPort interface.
 * Provides database access logic for Category entities using JPA.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    private final EntityManager entityManager;

    /**
     * Finds a category by its name, ignoring case differences.
     *
     * @param name the name of the category to search for
     * @return the matching DomainCategoryEntity, or null if no match is found
     */
    @Override
    public DomainCategoryEntity findByNameIgnoreCase(String name) {
        try {
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(
                    "SELECT c FROM DomainCategoryEntity c WHERE LOWER(c.name) = LOWER(:name)", DomainCategoryEntity.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}