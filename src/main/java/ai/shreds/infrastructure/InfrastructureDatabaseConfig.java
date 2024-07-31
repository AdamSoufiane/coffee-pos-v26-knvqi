package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.context.annotation.Primary; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
  
 @Configuration 
 @EnableTransactionManagement 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
 public class InfrastructureDatabaseConfig { 
  
     @Value("${spring.datasource.url}") 
     private String dbUrl; 
  
     @Value("${spring.datasource.username}") 
     private String dbUsername; 
  
     @Value("${spring.datasource.password}") 
     private String dbPassword; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String dbDriverClassName; 
  
     /** 
      * Configures the DataSource bean for database connection. 
      * @return DataSource object configured with the necessary database connection properties. 
      */ 
     @Bean 
     @Primary 
     public DataSource dataSource() { 
         return DataSourceBuilder.create() 
                 .url(dbUrl) 
                 .username(dbUsername) 
                 .password(dbPassword) 
                 .driverClassName(dbDriverClassName) 
                 .build(); 
     } 
  
     /** 
      * Configures the EntityManagerFactory bean for managing JPA entities. 
      * @param builder EntityManagerFactoryBuilder 
      * @return LocalContainerEntityManagerFactoryBean configured with the DataSource and other JPA properties. 
      */ 
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) { 
         return builder 
                 .dataSource(dataSource()) 
                 .packages("ai.shreds.domain") 
                 .persistenceUnit("shredsPU") 
                 .build(); 
     } 
  
     /** 
      * Configures the TransactionManager bean for handling transactions. 
      * @param entityManagerFactory LocalContainerEntityManagerFactoryBean 
      * @return PlatformTransactionManager configured with the EntityManagerFactory. 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) { 
         return new JpaTransactionManager(entityManagerFactory.getObject()); 
     } 
 } 
 