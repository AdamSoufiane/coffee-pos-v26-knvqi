package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationSyncServicePort; 
 import ai.shreds.application.ApplicationRealTimeUpdateServicePort; 
 import ai.shreds.shared.AdapterProductSyncRequestParams; 
 import ai.shreds.shared.AdapterProductSyncResponseDTO; 
 import ai.shreds.shared.AdapterRealTimeUpdateRequest; 
 import ai.shreds.shared.AdapterRealTimeUpdateResponse; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
  
 @RestController 
 @RequestMapping("/sync") 
 @RequiredArgsConstructor 
 public class AdapterProductSyncController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductSyncController.class); 
  
     private final ApplicationSyncServicePort applicationSyncServicePort; 
     private final ApplicationRealTimeUpdateServicePort applicationRealTimeUpdateServicePort; 
  
     @PostMapping 
     public AdapterProductSyncResponseDTO handleSyncRequest(@RequestBody AdapterProductSyncRequestParams params) { 
         try { 
             validateParams(params); 
             logger.info("Received sync request: {}", params); 
             AdapterProductSyncResponseDTO response = applicationSyncServicePort.syncProductData(params); 
             logger.info("Sync response: {}", response); 
             return response; 
         } catch (IllegalArgumentException e) { 
             logger.error("Validation error handling sync request", e); 
             return new AdapterProductSyncResponseDTO("Synchronization failed: " + e.getMessage(), null); 
         } catch (Exception e) { 
             logger.error("Error handling sync request", e); 
             return new AdapterProductSyncResponseDTO("Synchronization failed: Internal server error", null); 
         } 
     } 
  
     @PostMapping("/realtime-update") 
     public AdapterRealTimeUpdateResponse handleRealTimeUpdate(@RequestBody AdapterRealTimeUpdateRequest message) { 
         try { 
             validateMessage(message); 
             logger.info("Received real-time update message: {}", message); 
             AdapterRealTimeUpdateResponse response = applicationRealTimeUpdateServicePort.handleRealTimeUpdate(message); 
             logger.info("Real-time update response: {}", response); 
             return response; 
         } catch (IllegalArgumentException e) { 
             logger.error("Validation error handling real-time update", e); 
             return new AdapterRealTimeUpdateResponse("Real-time update failed: " + e.getMessage(), null); 
         } catch (Exception e) { 
             logger.error("Error handling real-time update", e); 
             return new AdapterRealTimeUpdateResponse("Real-time update failed: Internal server error", null); 
         } 
     } 
  
     private void validateParams(AdapterProductSyncRequestParams params) { 
         if (params.getId() == null || params.getName() == null || params.getPrice() == null || params.getAvailability() == null) { 
             throw new IllegalArgumentException("Invalid product data: Missing required fields"); 
         } 
         if (params.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new IllegalArgumentException("Invalid product data: Price must be a positive value"); 
         } 
     } 
  
     private void validateMessage(AdapterRealTimeUpdateRequest message) { 
         if (message.getId() == null || message.getName() == null || message.getPrice() == null || message.getAvailability() == null) { 
             throw new IllegalArgumentException("Invalid product data: Missing required fields"); 
         } 
         if (message.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new IllegalArgumentException("Invalid product data: Price must be a positive value"); 
         } 
     } 
 } 
 