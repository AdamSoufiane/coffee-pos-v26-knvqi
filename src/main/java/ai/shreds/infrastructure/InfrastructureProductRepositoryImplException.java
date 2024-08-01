package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.dao.DataIntegrityViolationException; 
 import org.springframework.dao.DuplicateKeyException; 
 import org.springframework.dao.OptimisticLockingFailureException; 
 import org.springframework.transaction.TransactionSystemException; 
 import org.springframework.stereotype.Component; 
  
 import java.sql.SQLIntegrityConstraintViolationException; 
  
 /** 
  * Handles exceptions that occur within the InfrastructureProductRepositoryImpl class. 
  */ 
 @Component 
 public class InfrastructureProductRepositoryImplException { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureProductRepositoryImplException.class); 
  
     /** 
      * Handles general exceptions and delegates to specific handlers if applicable. 
      *  
      * @param e the exception to handle 
      */ 
     public void handleException(Exception e) { 
         if (e instanceof DataAccessException) { 
             handleDataAccessException((DataAccessException) e); 
         } else if (e instanceof TransactionSystemException) { 
             handleTransactionSystemException((TransactionSystemException) e); 
         } else if (e instanceof SQLIntegrityConstraintViolationException) { 
             handleSQLIntegrityConstraintViolationException((SQLIntegrityConstraintViolationException) e); 
         } else if (e instanceof OptimisticLockingFailureException) { 
             handleOptimisticLockingFailureException((OptimisticLockingFailureException) e); 
         } else { 
             handleGeneralException(e); 
         } 
     } 
  
     /** 
      * Handles DataAccessException and its specific subtypes. 
      *  
      * @param e the DataAccessException to handle 
      */ 
     private void handleDataAccessException(DataAccessException e) { 
         if (e instanceof DataIntegrityViolationException) { 
             logger.error("Data integrity violation: {}", e.getMessage(), e); 
         } else if (e instanceof DuplicateKeyException) { 
             logger.error("Duplicate key exception: {}", e.getMessage(), e); 
         } else { 
             logger.error("Data access exception occurred: {}", e.getMessage(), e); 
         } 
         // Additional logic to handle DataAccessException can be added here 
     } 
  
     /** 
      * Handles TransactionSystemException. 
      *  
      * @param e the TransactionSystemException to handle 
      */ 
     private void handleTransactionSystemException(TransactionSystemException e) { 
         logger.error("Transaction system exception occurred: {}", e.getMessage(), e); 
         // Additional logic to handle TransactionSystemException can be added here 
     } 
  
     /** 
      * Handles SQLIntegrityConstraintViolationException. 
      *  
      * @param e the SQLIntegrityConstraintViolationException to handle 
      */ 
     private void handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) { 
         logger.error("SQL integrity constraint violation: {}", e.getMessage(), e); 
         // Additional logic to handle SQLIntegrityConstraintViolationException can be added here 
     } 
  
     /** 
      * Handles OptimisticLockingFailureException. 
      *  
      * @param e the OptimisticLockingFailureException to handle 
      */ 
     private void handleOptimisticLockingFailureException(OptimisticLockingFailureException e) { 
         logger.error("Optimistic locking failure: {}", e.getMessage(), e); 
         // Additional logic to handle OptimisticLockingFailureException can be added here 
     } 
  
     /** 
      * Handles general exceptions. 
      *  
      * @param e the exception to handle 
      */ 
     private void handleGeneralException(Exception e) { 
         logger.error("An unexpected error occurred: {}", e.getMessage(), e); 
         // Additional logic to handle general exceptions can be added here 
     } 
 } 
 