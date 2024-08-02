package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
 import org.springframework.transaction.jta.JtaTransactionManager; 
 import lombok.extern.slf4j.Slf4j; 
  
 import javax.transaction.TransactionManager; 
 import javax.transaction.UserTransaction; 
  
 /** 
  * Configuration class for managing transactions within the infrastructure layer. 
  */ 
 @Configuration 
 @EnableTransactionManagement 
 @Slf4j 
 public class InfrastructurePlatformTransactionManager { 
  
     private final UserTransaction userTransaction; 
     private final TransactionManager transactionManager; 
  
     public InfrastructurePlatformTransactionManager(UserTransaction userTransaction, TransactionManager transactionManager) { 
         this.userTransaction = userTransaction; 
         this.transactionManager = transactionManager; 
     } 
  
     /** 
      * Bean definition for PlatformTransactionManager. 
      * 
      * @return a configured JtaTransactionManager instance 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         try { 
             return new JtaTransactionManager(userTransaction, transactionManager); 
         } catch (Exception e) { 
             log.error("Error creating JtaTransactionManager", e); 
             throw new RuntimeException("Failed to create JtaTransactionManager", e); 
         } 
     } 
 } 
 