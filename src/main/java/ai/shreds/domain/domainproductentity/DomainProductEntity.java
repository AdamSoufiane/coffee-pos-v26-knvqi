package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainProductEntity {
    @Id
    @NotNull
    private UUID id;

    @NotNull
    @Size(max = 255)
    @Column(length = 255)
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    private Boolean availability;
}