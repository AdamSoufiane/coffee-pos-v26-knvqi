package ai.shreds.domain;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainProductSearchService {
    private final DomainProductRepositoryPort productRepository;
    private final DomainCategoryRepositoryPort categoryRepository;

    public List<DomainProductEntity> searchProductsByName(String name) {
        validateInput(name);
        log.info("Searching products by name: {}", name);
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<DomainProductEntity> searchProductsByDescription(String description) {
        validateInput(description);
        log.info("Searching products by description: {}", description);
        return productRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<DomainProductEntity> searchProductsByCategory(String category) {
        validateInput(category);
        log.info("Searching products by category: {}", category);
        Optional<DomainCategoryEntity> categoryEntity = categoryRepository.findByNameIgnoreCase(category);
        if (!categoryEntity.isPresent()) {
            log.warn("Category not found: {}", category);
            throw new IllegalArgumentException("Category not found");
        }
        return productRepository.findByCategoryNameIgnoreCase(categoryEntity.get().getName());
    }

    private void validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            log.error("Invalid input: {}", input);
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
    }
}