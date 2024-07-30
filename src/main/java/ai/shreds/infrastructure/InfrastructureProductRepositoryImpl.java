package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    @Autowired
    private ProductJpaRepository jpaRepository;

    @Override
    public List<DomainProductEntity> findByNameContainingIgnoreCase(String name) {
        return jpaRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<DomainProductEntity> findByDescriptionContainingIgnoreCase(String description) {
        return jpaRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @Override
    public List<DomainProductEntity> findByCategoryNameIgnoreCase(String category) {
        return jpaRepository.findByCategoryNameIgnoreCase(category);
    }
}

interface ProductJpaRepository extends JpaRepository<DomainProductEntity, UUID> {
    List<DomainProductEntity> findByNameContainingIgnoreCase(String name);
    List<DomainProductEntity> findByDescriptionContainingIgnoreCase(String description);
    List<DomainProductEntity> findByCategoryNameIgnoreCase(String category);
}