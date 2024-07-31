package ai.shreds.shared; 
  
 import lombok.Getter; 
 import lombok.RequiredArgsConstructor; 
  
 import java.util.UUID; 
  
 @Getter 
 @RequiredArgsConstructor 
 public class SharedUUIDValueObject { 
     private final UUID value; 
  
     public SharedUUIDValueObject(String uuid) { 
         if (uuid == null) { 
             throw new IllegalArgumentException("UUID cannot be null"); 
         } 
         this.value = UUID.fromString(uuid); 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         SharedUUIDValueObject that = (SharedUUIDValueObject) o; 
         return value.equals(that.value); 
     } 
  
     @Override 
     public int hashCode() { 
         return value.hashCode(); 
     } 
  
     @Override 
     public String toString() { 
         return value.toString(); 
     } 
 }