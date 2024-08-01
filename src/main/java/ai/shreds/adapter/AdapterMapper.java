package ai.shreds.adapter;

import ai.shreds.domain.DomainProductDomainEntity;
import ai.shreds.shared.AdapterRequestParam;
import ai.shreds.shared.AdapterResponseDTO;
import ai.shreds.shared.AdapterRealTimeMessage;
import javax.validation.constraints.Size;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.UUID;

@Mapper
public interface AdapterMapper {
    AdapterMapper INSTANCE = Mappers.getMapper(AdapterMapper.class);

    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    DomainProductDomainEntity toDomainEntity(AdapterRequestParam dto);

    AdapterResponseDTO toAdapterResponseDTO(DomainProductDomainEntity entity);

    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    DomainProductDomainEntity toDomainEntity(AdapterRealTimeMessage message);
}