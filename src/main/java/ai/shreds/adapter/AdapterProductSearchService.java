package ai.shreds.adapter;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductSearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdapterProductSearchService {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductSearchService.class);
    private final DomainProductSearchService domainProductSearchService;

    public List<DomainProductEntity> searchProductsByName(String name) {
        validateParameter(name, "Name");
        logger.info("Searching products by name: {}", name);
        return domainProductSearchService.searchProductsByName(name);
    }

    public List<DomainProductEntity> searchProductsByDescription(String description) {
        validateParameter(description, "Description");
        logger.info("Searching products by description: {}", description);
        return domainProductSearchService.searchProductsByDescription(description);
    }

    public List<DomainProductEntity> searchProductsByCategory(String category) {
        validateParameter(category, "Category");
        logger.info("Searching products by category: {}", category);
        return domainProductSearchService.searchProductsByCategory(category);
    }

    private void validateParameter(String parameter, String parameterName) {
        if (parameter == null || parameter.trim().isEmpty()) {
            logger.error("Invalid {} parameter: {}", parameterName, parameter);
            throw new IllegalArgumentException(parameterName + " parameter cannot be null or empty");
        }
    }
}