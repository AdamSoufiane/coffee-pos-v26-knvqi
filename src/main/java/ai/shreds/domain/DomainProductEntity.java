package ai.shreds.domain;

import ai.shreds.application.ApplicationSharedProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @DecimalMax(value = "99999999.99", message = "Price cannot be greater than 99999999.99")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull(message = "Availability cannot be null")
    @Column(name = "availability", nullable = false)
    private Boolean availability;

    @NotNull(message = "Category ID cannot be null")
    @Column(name = "category_id", nullable = false)
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

    public void validateProductData() {
        // Validation is now handled by the annotations
    }
}