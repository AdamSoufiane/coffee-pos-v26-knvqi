package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  
 import javax.sql.DataSource; 
 import org.apache.commons.dbcp2.BasicDataSource; 
  
 @Configuration 
 @EnableJpaRepositories(basePackages = "ai.shreds.domain") 
 @EnableTransactionManagement 
 public class InfrastructureDatabaseConfig { 
  
     @Value("${spring.datasource.url}") 
     private String databaseUrl; 
  
     @Value("${spring.datasource.username}") 
     private String databaseUsername; 
  
     @Value("${spring.datasource.password}") 
     private String databasePassword; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String databaseDriverClassName; 
  
     @Bean 
     public DataSource dataSource() { 
         BasicDataSource dataSource = new BasicDataSource(); 
         dataSource.setUrl(databaseUrl); 
         dataSource.setUsername(databaseUsername); 
         dataSource.setPassword(databasePassword); 
         dataSource.setDriverClassName(databaseDriverClassName); 
         // Set connection pooling properties 
         dataSource.setInitialSize(5); 
         dataSource.setMaxTotal(10); 
         return dataSource; 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
         entityManagerFactoryBean.setDataSource(dataSource()); 
         entityManagerFactoryBean.setPackagesToScan("ai.shreds.domain"); 
         entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
         return entityManagerFactoryBean; 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
  
     // Add proper validation and exception handling for database properties 
     @PostConstruct 
     public void validateDatabaseProperties() { 
         if (databaseUrl == null || databaseUrl.isEmpty()) { 
             throw new IllegalArgumentException("Database URL must not be null or empty"); 
         } 
         if (databaseUsername == null || databaseUsername.isEmpty()) { 
             throw new IllegalArgumentException("Database Username must not be null or empty"); 
         } 
         if (databasePassword == null || databasePassword.isEmpty()) { 
             throw new IllegalArgumentException("Database Password must not be null or empty"); 
         } 
         if (databaseDriverClassName == null || databaseDriverClassName.isEmpty()) { 
             throw new IllegalArgumentException("Database Driver Class Name must not be null or empty"); 
         } 
     } 
  
     // Include comments to explain the purpose of each bean method 
     /** 
      * Configures the DataSource bean for database connection pooling. 
      */ 
     @Bean 
     public DataSource dataSource() { 
         BasicDataSource dataSource = new BasicDataSource(); 
         dataSource.setUrl(databaseUrl); 
         dataSource.setUsername(databaseUsername); 
         dataSource.setPassword(databasePassword); 
         dataSource.setDriverClassName(databaseDriverClassName); 
         // Set connection pooling properties 
         dataSource.setInitialSize(5); 
         dataSource.setMaxTotal(10); 
         return dataSource; 
     } 
  
     /** 
      * Configures the EntityManagerFactory bean for JPA entity management. 
      */ 
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
         entityManagerFactoryBean.setDataSource(dataSource()); 
         entityManagerFactoryBean.setPackagesToScan("ai.shreds.domain"); 
         entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
         return entityManagerFactoryBean; 
     } 
  
     /** 
      * Configures the TransactionManager bean for transaction management. 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
 } 
 