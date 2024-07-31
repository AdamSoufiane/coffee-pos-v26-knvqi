package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedUUIDValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.application.ApplicationSharedCategoryDTO; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Represents a category in the catalogue. 
  * Each field is represented by a value object. 
  */ 
 @Data 
 @NoArgsConstructor 
 public class DomainCategoryEntity { 
     private SharedUUIDValueObject id; 
     private SharedStringValueObject name; 
     private SharedStringValueObject description; 
  
     public DomainCategoryEntity(SharedUUIDValueObject id, SharedStringValueObject name, SharedStringValueObject description) { 
         this.id = id; 
         this.name = name; 
         this.description = description; 
     } 
  
     /** 
      * Converts the DomainCategoryEntity instance to a SharedCategoryDTO instance. 
      * @return ApplicationSharedCategoryDTO 
      */ 
     public ApplicationSharedCategoryDTO toSharedCategoryDTO() { 
         ApplicationSharedCategoryDTO dto = new ApplicationSharedCategoryDTO(); 
         dto.setId(this.id.getValue()); 
         dto.setName(this.name.getValue()); 
         dto.setDescription(this.description.getValue()); 
         return dto; 
     } 
  
     /** 
      * Creates a DomainCategoryEntity instance from a SharedCategoryDTO instance. 
      * @param dto ApplicationSharedCategoryDTO 
      * @return DomainCategoryEntity 
      */ 
     public static DomainCategoryEntity fromSharedCategoryDTO(ApplicationSharedCategoryDTO dto) { 
         return new DomainCategoryEntity( 
                 new SharedUUIDValueObject(dto.getId()), 
                 new SharedStringValueObject(dto.getName()), 
                 new SharedStringValueObject(dto.getDescription()) 
         ); 
     } 
 } 
 