package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Configuration class for setting up repository beans.
 */
@Configuration
public class InfrastructureRepositoryConfig {

    private final InfrastructureProductRepositoryImpl productRepositoryImpl;
    private final InfrastructureCategoryRepositoryImpl categoryRepositoryImpl;

    @Autowired
    public InfrastructureRepositoryConfig(InfrastructureProductRepositoryImpl productRepositoryImpl, InfrastructureCategoryRepositoryImpl categoryRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }

    /**
     * Bean definition for product repository.
     *
     * @return the product repository port
     */
    @Bean
    public DomainProductRepositoryPort productRepository() {
        return productRepositoryImpl;
    }

    /**
     * Bean definition for category repository.
     *
     * @return the category repository port
     */
    @Bean
    public DomainCategoryRepositoryPort categoryRepository() {
        return categoryRepositoryImpl;
    }
}