package ai.shreds.domain;

import ai.shreds.application.ApplicationSharedProductDTO;
import ai.shreds.shared.SharedUUIDValueObject;
import ai.shreds.shared.SharedStringValueObject;
import ai.shreds.shared.SharedDecimalValueObject;
import ai.shreds.shared.SharedBooleanValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DomainProductMapper {

    private static final Logger logger = LoggerFactory.getLogger(DomainProductMapper.class);
    private final DomainProductRepositoryPort productRepository;

    public DomainProductMapper(DomainProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Converts a DomainProductEntity to ApplicationSharedProductDTO.
     *
     * @param product the domain product entity
     * @return the shared product DTO
     */
    public ApplicationSharedProductDTO toSharedDTO(DomainProductEntity product) {
        if (product == null) {
            logger.error("Product entity cannot be null.");
            throw new IllegalArgumentException("Product entity cannot be null.");
        }
        logger.info("Converting DomainProductEntity to ApplicationSharedProductDTO.");
        return new ApplicationSharedProductDTO(
            product.getId().getValue(),
            product.getName().getValue(),
            product.getDescription().getValue(),
            product.getPrice().getValue(),
            product.getAvailability().getValue(),
            product.getCategoryId().getValue()
        );
    }

    /**
     * Converts an ApplicationSharedProductDTO to DomainProductEntity.
     *
     * @param dto the shared product DTO
     * @return the domain product entity
     */
    public DomainProductEntity toDomainEntity(ApplicationSharedProductDTO dto) {
        if (dto == null) {
            logger.error("Product DTO cannot be null.");
            throw new IllegalArgumentException("Product DTO cannot be null.");
        }
        if (dto.getPrice() <= 0) {
            logger.error("The price of the product must be a positive value.");
            throw new IllegalArgumentException("The price of the product must be a positive value.");
        }
        if (!isProductNameUnique(dto.getName())) {
            logger.error("The name '{}' is already taken. Please choose a unique name.", dto.getName());
            throw new IllegalArgumentException("The name '" + dto.getName() + "' is already taken. Please choose a unique name.");
        }
        logger.info("Converting ApplicationSharedProductDTO to DomainProductEntity.");
        return new DomainProductEntity(
            new SharedUUIDValueObject(dto.getId()),
            new SharedStringValueObject(dto.getName()),
            new SharedStringValueObject(dto.getDescription()),
            new SharedDecimalValueObject(dto.getPrice()),
            new SharedBooleanValueObject(dto.getAvailability()),
            new SharedUUIDValueObject(dto.getCategoryId())
        );
    }

    /**
     * Checks if the product name is unique within the catalogue.
     *
     * @param name the product name
     * @return true if the product name is unique, false otherwise
     */
    private boolean isProductNameUnique(String name) {
        logger.info("Checking if product name '{}' is unique.", name);
        return productRepository.findByName(new SharedStringValueObject(name)).isPresent();
    }
}