package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterRequestParam; 
 import ai.shreds.adapter.AdapterResponseDTO; 
 import ai.shreds.adapter.AdapterRealTimeMessage; 
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
  
 // Implementation Note: Add Lombok annotations for getters and setters in DomainProductDomainEntity class.