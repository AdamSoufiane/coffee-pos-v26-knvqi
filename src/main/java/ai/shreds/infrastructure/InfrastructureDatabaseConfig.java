package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import java.util.Properties; 
  
 @Configuration 
 @EnableTransactionManagement 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
 public class InfrastructureDatabaseConfig { 
  
     @Bean 
     public DataSource dataSource() { 
         try { 
             return DataSourceBuilder.create() 
                     .url("jdbc:postgresql://localhost:5432/productdb") 
                     .username("username") 
                     .password("password") 
                     .driverClassName("org.postgresql.Driver") 
                     .build(); 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to create DataSource", e); 
         } 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         try { 
             LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
             em.setDataSource(dataSource()); 
             em.setPackagesToScan("ai.shreds.domain"); 
             em.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
             em.setJpaProperties(hibernateProperties()); 
             return em; 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to set up EntityManagerFactory", e); 
         } 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         try { 
             JpaTransactionManager transactionManager = new JpaTransactionManager(); 
             transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
             return transactionManager; 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to set up TransactionManager", e); 
         } 
     } 
  
     private Properties hibernateProperties() { 
         Properties properties = new Properties(); 
         properties.setProperty("hibernate.hbm2ddl.auto", "update"); 
         properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); 
         return properties; 
     } 
 } 
 