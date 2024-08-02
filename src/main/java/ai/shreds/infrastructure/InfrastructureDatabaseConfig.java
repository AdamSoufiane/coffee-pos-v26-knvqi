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
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 import javax.sql.DataSource; 
 import com.zaxxer.hikari.HikariConfig; 
 import com.zaxxer.hikari.HikariDataSource; 
  
 @Configuration 
 @EnableTransactionManagement 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
 public class InfrastructureDatabaseConfig { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class); 
  
     @Value("${spring.datasource.url}") 
     private String dbUrl; 
  
     @Value("${spring.datasource.username}") 
     private String dbUsername; 
  
     @Value("${spring.datasource.password}") 
     private String dbPassword; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String dbDriverClassName; 
  
     @Bean 
     public DataSource dataSource() { 
         logger.info("Initializing DataSource..."); 
         HikariConfig hikariConfig = new HikariConfig(); 
         hikariConfig.setJdbcUrl(dbUrl); 
         hikariConfig.setUsername(dbUsername); 
         hikariConfig.setPassword(dbPassword); 
         hikariConfig.setDriverClassName(dbDriverClassName); 
         return new HikariDataSource(hikariConfig); 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         logger.info("Initializing EntityManagerFactory..."); 
         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
         entityManagerFactoryBean.setDataSource(dataSource()); 
         entityManagerFactoryBean.setPackagesToScan("ai.shreds.domain"); 
         entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
         return entityManagerFactoryBean; 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         logger.info("Initializing TransactionManager..."); 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
 }