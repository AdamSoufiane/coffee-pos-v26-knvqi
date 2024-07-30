package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * Implementation of the DomainCategoryRepositoryPort interface.
 * Provides database access logic for Category entities using JPA.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImpl.class);

    /**
     * Finds a category by its name, ignoring case differences.
     *
     * @param name the name of the category to search for
     * @return the matching DomainCategoryEntity, or Optional.empty() if no match is found
     */
    @Override
    public Optional<DomainCategoryEntity> findByNameIgnoreCase(String name) {
        try {
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(
                    "SELECT c FROM DomainCategoryEntity c WHERE LOWER(c.name) = LOWER(:name)", DomainCategoryEntity.class);
            query.setParameter("name", name.toLowerCase());
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (PersistenceException e) {
            logger.error("Error occurred during database operation", e);
            return Optional.empty();
        }
    }
}