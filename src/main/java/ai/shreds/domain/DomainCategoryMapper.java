package ai.shreds.domain;

import ai.shreds.shared.SharedUUIDValueObject;
import ai.shreds.shared.SharedStringValueObject;
import ai.shreds.shared.SharedCategoryDTO;

public class DomainCategoryMapper {

    /**
     * Converts a DomainCategoryEntity object to a SharedCategoryDTO object.
     *
     * @param category the DomainCategoryEntity object to convert
     * @return the converted SharedCategoryDTO object
     */
    public SharedCategoryDTO toSharedDTO(DomainCategoryEntity category) {
        if (category == null) {
            return null;
        }
        SharedCategoryDTO dto = new SharedCategoryDTO();
        dto.setId(new SharedUUIDValueObject(category.getId()));
        dto.setName(new SharedStringValueObject(category.getName()));
        dto.setDescription(new SharedStringValueObject(category.getDescription()));
        return dto;
    }

    /**
     * Converts a SharedCategoryDTO object to a DomainCategoryEntity object.
     *
     * @param dto the SharedCategoryDTO object to convert
     * @return the converted DomainCategoryEntity object
     */
    public DomainCategoryEntity toDomainEntity(SharedCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        DomainCategoryEntity category = new DomainCategoryEntity(
                new SharedUUIDValueObject(dto.getId()),
                new SharedStringValueObject(dto.getName()),
                new SharedStringValueObject(dto.getDescription())
        );
        return category;
    }
}