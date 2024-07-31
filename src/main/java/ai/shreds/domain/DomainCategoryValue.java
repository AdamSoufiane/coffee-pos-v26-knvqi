package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.EqualsAndHashCode; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 /** 
  * Represents the value object for a category in the domain layer. 
  * This class is immutable and uses Lombok annotations for boilerplate code reduction. 
  */ 
 @Getter 
 @AllArgsConstructor 
 @EqualsAndHashCode 
 @ToString 
 public class DomainCategoryValue { 
     private final String name; 
     private final String description; 
 } 
 