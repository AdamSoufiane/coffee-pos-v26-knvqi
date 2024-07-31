package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Autowired
    public InfrastructureCategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Optional<DomainCategoryEntity> findById(UUID categoryId) {
        try {
            return jpaCategoryRepository.findById(categoryId).map(this::toDomainEntity);
        } catch (Exception e) {
            // Add proper logging or exception handling logic here
            return Optional.empty();
        }
    }

    private DomainCategoryEntity toDomainEntity(CategoryEntity categoryEntity) {
        return new DomainCategoryEntity(
                new SharedUUIDValueObject(categoryEntity.getId()),
                new SharedStringValueObject(categoryEntity.getName()),
                new SharedStringValueObject(categoryEntity.getDescription())
        );
    }
}

interface JpaCategoryRepository extends org.springframework.data.jpa.repository.JpaRepository<CategoryEntity, UUID> {}
