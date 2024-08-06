package ai.shreds.adapter;

import ai.shreds.domain.DomainProductDTO;
import ai.shreds.shared.AdapterRequestParam;
import ai.shreds.shared.AdapterResponseDTO;
import ai.shreds.shared.AdapterRealTimeMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.UUID;
import java.time.LocalDateTime;

@Mapper
public interface AdapterMapper {
    AdapterMapper INSTANCE = Mappers.getMapper(AdapterMapper.class);

    @Mapping(target = "created_at", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updated_at", expression = "java(LocalDateTime.now())")
    DomainProductDTO toDomainEntity(AdapterRequestParam dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "description", source = "entity.description")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "availability", source = "entity.availability")
    AdapterResponseDTO toAdapterResponseDTO(DomainProductDTO entity);

    @Mapping(target = "created_at", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updated_at", expression = "java(LocalDateTime.now())")
    DomainProductDTO toDomainEntity(AdapterRealTimeMessage message);
}