package ai.shreds.application;

import ai.shreds.adapter.AdapterProductResponseDTO;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductSearchService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApplicationProductSearchService implements ApplicationProductSearchServiceInputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProductSearchService.class);
    private final DomainProductSearchService domainProductSearchService;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<AdapterProductResponseDTO> searchByName(String name) {
        validateQueryParameter(name, "name");
        try {
            logger.info("Searching products by name: {}", name);
            Pageable pageable = PageRequest.of(0, 10); // Example: first page with 10 items per page
            List<DomainProductEntity> products = domainProductSearchService.searchProductsByName(name);
            return products.stream()
                    .map(productMapper::toAdapterProductResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error searching products by name: {}", name, e);
            throw new RuntimeException("Error searching products by name", e);
        }
    }

    @Override
    public List<AdapterProductResponseDTO> searchByDescription(String description) {
        validateQueryParameter(description, "description");
        try {
            logger.info("Searching products by description: {}", description);
            Pageable pageable = PageRequest.of(0, 10); // Example: first page with 10 items per page
            List<DomainProductEntity> products = domainProductSearchService.searchProductsByDescription(description);
            return products.stream()
                    .map(productMapper::toAdapterProductResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error searching products by description: {}", description, e);
            throw new RuntimeException("Error searching products by description", e);
        }
    }

    @Override
    public List<AdapterProductResponseDTO> searchByCategory(String category) {
        validateQueryParameter(category, "category");
        try {
            logger.info("Searching products by category: {}", category);
            Pageable pageable = PageRequest.of(0, 10); // Example: first page with 10 items per page
            List<DomainProductEntity> products = domainProductSearchService.searchProductsByCategory(category);
            return products.stream()
                    .map(productMapper::toAdapterProductResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error searching products by category: {}", category, e);
            throw new RuntimeException("Error searching products by category", e);
        }
    }

    private void validateQueryParameter(String parameter, String parameterName) {
        if (parameter == null || parameter.trim().isEmpty()) {
            throw new IllegalArgumentException("The " + parameterName + " parameter must not be null or empty");
        }
    }
}

@Mapper
interface ProductMapper {
    AdapterProductResponseDTO toAdapterProductResponseDTO(DomainProductEntity product);
}