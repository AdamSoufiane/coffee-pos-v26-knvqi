package ai.shreds.domain;

import ai.shreds.application.ApplicationSharedProductDTO;
import ai.shreds.shared.SharedUUIDValueObject;
import ai.shreds.shared.SharedStringValueObject;
import ai.shreds.shared.SharedDecimalValueObject;
import ai.shreds.shared.SharedBooleanValueObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class DomainProductEntity {
    @Id
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters.")
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters.")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be null.")
    private BigDecimal price;

    @Column(name = "availability", nullable = false)
    @NotNull(message = "Availability cannot be null.")
    private Boolean availability;

    @Column(name = "category_id", nullable = false)
    @NotNull(message = "Category ID cannot be null.")
    private UUID categoryId;

    public ApplicationSharedProductDTO toSharedProductDTO() {
        return ApplicationSharedProductDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .availability(this.availability)
                .categoryId(this.categoryId)
                .build();
    }

    public static DomainProductEntity fromSharedProductDTO(ApplicationSharedProductDTO dto) {
        return DomainProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .availability(dto.getAvailability())
                .categoryId(dto.getCategoryId())
                .build();
    }

    public void validateProductData(DomainProductRepositoryPort productRepository, DomainCategoryRepositoryPort categoryRepository) {
        if (productRepository.existsByName(this.name)) {
            throw new IllegalArgumentException("Product name must be unique.");
        }
        if (this.price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value.");
        }
        if (!categoryRepository.existsById(this.categoryId)) {
            throw new IllegalArgumentException("Category ID must exist in the database.");
        }
    }
}