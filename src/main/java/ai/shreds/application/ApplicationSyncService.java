package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterProductSyncRequestParams; 
 import ai.shreds.adapter.AdapterProductSyncResponseDTO; 
 import ai.shreds.domain.DomainProductDomainEntity; 
 import ai.shreds.domain.DomainProductSyncServicePort; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import ai.shreds.shared.AdapterMapper; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
  
 @Slf4j 
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationSyncService implements ApplicationSyncServicePort { 
     private final DomainProductSyncServicePort domainProductSyncServicePort; 
     private final DomainProductRepositoryPort domainProductRepositoryPort; 
     private final AdapterMapper adapterMapper; 
  
     @Override 
     public AdapterProductSyncResponseDTO syncProductData(AdapterProductSyncRequestParams params) { 
         try { 
             // Validate input parameters 
             if (params == null || params.getId() == null) { 
                 throw new IllegalArgumentException("Invalid product data"); 
             } 
  
             // Convert to domain entity 
             DomainProductDomainEntity domainEntity = adapterMapper.toDomainEntity(params); 
  
             // Validate domain entity 
             if (!domainProductSyncServicePort.validateProductData(domainEntity)) { 
                 throw new IllegalArgumentException("Invalid product data"); 
             } 
  
             // Transform domain entity if necessary 
             DomainProductDomainEntity transformedEntity = domainProductSyncServicePort.transformProductData(domainEntity); 
  
             // Send sync request 
             domainProductSyncServicePort.sendSyncRequest(transformedEntity); 
  
             // Publish real-time update 
             domainProductSyncServicePort.publishRealTimeUpdate(transformedEntity); 
  
             // Convert to response DTO 
             AdapterProductSyncResponseDTO responseDTO = adapterMapper.toAdapterResponseDTO(transformedEntity); 
  
             return responseDTO; 
         } catch (Exception e) { 
             log.error("Error during product synchronization: ", e); 
             throw new RuntimeException("Synchronization failed", e); 
         } 
     } 
 } 
 