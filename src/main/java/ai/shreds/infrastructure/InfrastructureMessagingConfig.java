package ai.shreds.infrastructure; 
  
 import org.springframework.amqp.core.Queue; 
 import org.springframework.amqp.rabbit.annotation.EnableRabbit; 
 import org.springframework.amqp.rabbit.connection.CachingConnectionFactory; 
 import org.springframework.amqp.rabbit.core.RabbitTemplate; 
 import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer; 
 import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.context.annotation.Profile; 
 import org.springframework.kafka.annotation.EnableKafka; 
 import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory; 
 import org.springframework.kafka.core.ConsumerFactory; 
 import org.springframework.kafka.core.DefaultKafkaConsumerFactory; 
 import org.springframework.kafka.core.DefaultKafkaProducerFactory; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.kafka.core.ProducerFactory; 
 import org.springframework.kafka.listener.ConcurrentMessageListenerContainer; 
 import org.springframework.kafka.listener.MessageListener; 
 import org.springframework.kafka.listener.config.ContainerProperties; 
 import lombok.Getter; 
 import lombok.Setter; 
 import java.util.HashMap; 
 import java.util.Map; 
  
 @Configuration 
 @EnableRabbit 
 @EnableKafka 
 @Getter 
 @Setter 
 public class InfrastructureMessagingConfig { 
  
     @Value("${rabbitmq.host}") 
     private String rabbitMqHost; 
  
     @Value("${rabbitmq.username}") 
     private String rabbitMqUsername; 
  
     @Value("${rabbitmq.password}") 
     private String rabbitMqPassword; 
  
     @Value("${kafka.bootstrap.servers}") 
     private String kafkaBootstrapServers; 
  
     /** 
      * RabbitMQ Configuration 
      */ 
     @Bean 
     @Profile("rabbitmq") 
     public CachingConnectionFactory rabbitConnectionFactory() { 
         try { 
             CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqHost); 
             connectionFactory.setUsername(rabbitMqUsername); 
             connectionFactory.setPassword(rabbitMqPassword); 
             return connectionFactory; 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to configure RabbitMQ connection factory", e); 
         } 
     } 
  
     @Bean 
     @Profile("rabbitmq") 
     public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) { 
         return new RabbitTemplate(connectionFactory); 
     } 
  
     @Bean 
     @Profile("rabbitmq") 
     public Queue queue() { 
         return new Queue("product_updates", false); 
     } 
  
     @Bean 
     @Profile("rabbitmq") 
     public SimpleMessageListenerContainer rabbitListenerContainer(CachingConnectionFactory connectionFactory, 
                                                                   MessageListenerAdapter listenerAdapter) { 
         SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(); 
         container.setConnectionFactory(connectionFactory); 
         container.setQueueNames("product_updates"); 
         container.setMessageListener(listenerAdapter); 
         return container; 
     } 
  
     @Bean 
     @Profile("rabbitmq") 
     public MessageListenerAdapter listenerAdapter(MessageListener listener) { 
         return new MessageListenerAdapter(listener, "handleRealTimeUpdate"); 
     } 
  
     /** 
      * Kafka Configuration 
      */ 
     @Bean 
     @Profile("kafka") 
     public Map<String, Object> consumerConfigs() { 
         Map<String, Object> props = new HashMap<>(); 
         props.put("bootstrap.servers", kafkaBootstrapServers); 
         props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
         props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
         props.put("group.id", "product_updates"); 
         return props; 
     } 
  
     @Bean 
     @Profile("kafka") 
     public ConsumerFactory<String, String> consumerFactory() { 
         return new DefaultKafkaConsumerFactory<>(consumerConfigs()); 
     } 
  
     @Bean 
     @Profile("kafka") 
     public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() { 
         ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>(); 
         factory.setConsumerFactory(consumerFactory()); 
         return factory; 
     } 
  
     @Bean 
     @Profile("kafka") 
     public Map<String, Object> producerConfigs() { 
         Map<String, Object> props = new HashMap<>(); 
         props.put("bootstrap.servers", kafkaBootstrapServers); 
         props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); 
         props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); 
         return props; 
     } 
  
     @Bean 
     @Profile("kafka") 
     public ProducerFactory<String, String> producerFactory() { 
         return new DefaultKafkaProducerFactory<>(producerConfigs()); 
     } 
  
     @Bean 
     @Profile("kafka") 
     public KafkaTemplate<String, String> kafkaTemplate() { 
         return new KafkaTemplate<>(producerFactory()); 
     } 
  
     @Bean 
     @Profile("kafka") 
     public ConcurrentMessageListenerContainer<String, String> kafkaListenerContainer(ConsumerFactory<String, String> consumerFactory, 
                                                                                      MessageListener<String, String> listener) { 
         ContainerProperties containerProps = new ContainerProperties("product_updates"); 
         containerProps.setMessageListener(listener); 
         return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProps); 
     } 
 } 
 