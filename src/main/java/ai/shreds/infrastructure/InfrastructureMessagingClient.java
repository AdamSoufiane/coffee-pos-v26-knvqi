package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductDomainEntity; 
 import ai.shreds.domain.DomainProductSyncServicePort; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.amqp.rabbit.core.RabbitTemplate; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.retry.annotation.Backoff; 
 import org.springframework.retry.annotation.Retryable; 
 import org.springframework.stereotype.Service; 
  
 @Service 
 @RequiredArgsConstructor 
 public class InfrastructureMessagingClient implements DomainProductSyncServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMessagingClient.class); 
  
     private final RabbitTemplate rabbitTemplate; 
     private final KafkaTemplate<String, DomainProductDomainEntity> kafkaTemplate; 
  
     private static final String SYNC_QUEUE = "sync-queue"; 
     private static final String REALTIME_UPDATE_TOPIC = "realtime-update-topic"; 
  
     @Override 
     @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000)) 
     public void sendSyncRequest(DomainProductDomainEntity entity) { 
         if (validateProductData(entity)) { 
             try { 
                 rabbitTemplate.convertAndSend(SYNC_QUEUE, entity); 
                 logger.info("Successfully sent sync request for product: {}", entity.getId()); 
             } catch (Exception e) { 
                 logger.error("Failed to send sync request for product: {}", entity.getId(), e); 
                 throw new InfrastructureMessagingException("Failed to send sync request", e); 
             } 
         } else { 
             logger.warn("Invalid product data for sync request: {}", entity.getId()); 
         } 
     } 
  
     @Override 
     @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000)) 
     public void publishRealTimeUpdate(DomainProductDomainEntity entity) { 
         if (validateProductData(entity)) { 
             try { 
                 kafkaTemplate.send(REALTIME_UPDATE_TOPIC, entity); 
                 logger.info("Successfully published real-time update for product: {}", entity.getId()); 
             } catch (Exception e) { 
                 logger.error("Failed to publish real-time update for product: {}", entity.getId(), e); 
                 throw new InfrastructureMessagingException("Failed to publish real-time update", e); 
             } 
         } else { 
             logger.warn("Invalid product data for real-time update: {}", entity.getId()); 
         } 
     } 
  
     private boolean validateProductData(DomainProductDomainEntity entity) { 
         return entity != null && entity.getPrice() != null && entity.getPrice().compareTo(BigDecimal.ZERO) > 0; 
     } 
 } 
  
 class InfrastructureMessagingException extends RuntimeException { 
     public InfrastructureMessagingException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }