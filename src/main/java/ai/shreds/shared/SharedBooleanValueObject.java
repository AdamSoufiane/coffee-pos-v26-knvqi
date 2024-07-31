package ai.shreds.shared; 
  
 import java.util.Objects; 
  
 public final class SharedBooleanValueObject { 
     private final Boolean value; 
  
     public SharedBooleanValueObject(Boolean value) { 
         if (value == null) { 
             throw new IllegalArgumentException("Boolean value cannot be null"); 
         } 
         this.value = value; 
     } 
  
     public Boolean getValue() { 
         return value; 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         SharedBooleanValueObject that = (SharedBooleanValueObject) o; 
         return Objects.equals(value, that.value); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(value); 
     } 
  
     @Override 
     public String toString() { 
         return "SharedBooleanValueObject{" + 
                 "value=" + value + 
                 '}'; 
     } 
 } 
 // implementation-note: Use Lombok annotations for getter and constructor.