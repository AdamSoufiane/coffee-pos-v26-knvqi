package ai.shreds.domain;

import ai.shreds.application.ApplicationSharedProductDTO;
import ai.shreds.shared.SharedUUIDValueObject;
import ai.shreds.shared.SharedStringValueObject;
import ai.shreds.shared.SharedDecimalValueObject;
import ai.shreds.shared.SharedBooleanValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class DomainProductMapper {

    private static final Logger logger = LoggerFactory.getLogger(DomainProductMapper.class);
    private final DomainProductRepositoryPort productRepository;

    public DomainProductMapper(DomainProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    public ApplicationSharedProductDTO toSharedDTO(DomainProductEntity product) {
        if (product == null) {
            logger.error("Product entity cannot be null.");
            throw new DomainException("Product entity cannot be null.");
        }
        logger.info("Converting DomainProductEntity to ApplicationSharedProductDTO.");
        return new ApplicationSharedProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getAvailability(),
            product.getCategoryId()
        );
    }

    public DomainProductEntity toDomainEntity(ApplicationSharedProductDTO dto) {
        if (dto == null) {
            logger.error("Product DTO cannot be null.");
            throw new DomainException("Product DTO cannot be null.");
        }
        if (dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("The price of the product must be a positive value.");
            throw new DomainException("The price of the product must be a positive value.");
        }
        if (!isProductNameUnique(dto.getName())) {
            logger.error("The name '{}' is already taken. Please choose a unique name.", dto.getName());
            throw new DomainException("The name '" + dto.getName() + "' is already taken. Please choose a unique name.");
        }
        logger.info("Converting ApplicationSharedProductDTO to DomainProductEntity.");
        return new DomainProductEntity(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getPrice(),
            dto.getAvailability(),
            dto.getCategoryId()
        );
    }

    private boolean isProductNameUnique(String name) {
        logger.info("Checking if product name '{}' is unique.", name);
        return productRepository.findByName(new SharedStringValueObject(name)).isEmpty();
    }
}