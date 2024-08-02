package ai.shreds.infrastructure;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

@Configuration
public class InfrastructureDataSource {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSource.class);
    private final Environment env;

    @Autowired
    public InfrastructureDataSource(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        try {
            DataSource dataSource = DataSourceBuilder.create()
                    .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
                    .url(env.getProperty("spring.datasource.url"))
                    .username(env.getProperty("spring.datasource.username"))
                    .password(env.getProperty("spring.datasource.password"))
                    .build();
            logger.info("DataSource configured successfully.");
            return dataSource;
        } catch (Exception e) {
            logger.error("Failed to configure DataSource", e);
            throw new DataSourceConfigurationException("Failed to configure DataSource", e);
        }
    }

    // Custom exception class for DataSource configuration errors
    public static class DataSourceConfigurationException extends RuntimeException {
        public DataSourceConfigurationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}