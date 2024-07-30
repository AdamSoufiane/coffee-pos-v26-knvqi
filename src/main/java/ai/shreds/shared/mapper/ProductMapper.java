package ai.shreds.shared.mapper;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.shared.SharedCreateProductRequestParams;
import ai.shreds.shared.SharedProductResponseDTO;
import java.util.UUID;

public class ProductMapper {

    public DomainProductEntity toDomainEntity(SharedCreateProductRequestParams requestParams) {
        return DomainProductEntity.builder()
                .id(UUID.randomUUID())
                .name(requestParams.getName())
                .description(requestParams.getDescription())
                .price(requestParams.getPrice())
                .availability(requestParams.getAvailability())
                .build();
    }

    public SharedProductResponseDTO toResponseDTO(DomainProductEntity productEntity) {
        return SharedProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .availability(productEntity.getAvailability())
                .build();
    }
}