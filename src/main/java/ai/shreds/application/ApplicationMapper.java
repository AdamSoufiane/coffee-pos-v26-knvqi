package ai.shreds.application;

import ai.shreds.shared.AdapterRequestParam;
import ai.shreds.shared.AdapterResponseDTO;
import ai.shreds.shared.AdapterRealTimeMessage;
import ai.shreds.domain.DomainProductDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "availability", target = "availability")
    DomainProductDomainEntity toDomainEntity(AdapterRequestParam dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "availability", target = "availability")
    AdapterResponseDTO toAdapterResponseDTO(DomainProductDomainEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "availability", target = "availability")
    DomainProductDomainEntity toDomainEntity(AdapterRealTimeMessage message);
}