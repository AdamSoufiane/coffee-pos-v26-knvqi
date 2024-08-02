package ai.shreds.adapter;

import ai.shreds.application.ApplicationRealTimeUpdateServicePort;
import ai.shreds.shared.AdapterRealTimeMessage;
import ai.shreds.adapter.AdapterMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

/**
 * Listener for handling real-time updates of product data using RabbitMQ/Kafka.
 */
@Component
@RequiredArgsConstructor
public class AdapterRealTimeUpdateListener {

    private static final Logger logger = LoggerFactory.getLogger(AdapterRealTimeUpdateListener.class);
    private final ApplicationRealTimeUpdateServicePort applicationRealTimeUpdateServicePort;
    private final AdapterMapper adapterMapper;

    /**
     * Handles real-time updates by listening to RabbitMQ and Kafka messages.
     * @param message The real-time update message containing product data.
     */
    @RabbitListener(queues = "product-update-queue")
    @KafkaListener(topics = "product-update-topic", groupId = "product-group")
    public void handleRealTimeUpdate(AdapterRealTimeMessage message) {
        try {
            // Validate the incoming message
            if (isValidMessage(message)) {
                var domainEntity = adapterMapper.toDomainEntity(message);
                applicationRealTimeUpdateServicePort.handleRealTimeUpdate(domainEntity);
            } else {
                logger.warn("Received invalid product data with message id: {}", message.getId());
            }
        } catch (Exception e) {
            // Log the exception and handle it appropriately
            logger.error("Error handling real-time update for message id: {}, ", message.getId(), e);
        }
    }

    /**
     * Validates the incoming message to ensure it contains valid product data.
     * @param message The real-time update message containing product data.
     * @return true if the message is valid, false otherwise.
     */
    private boolean isValidMessage(AdapterRealTimeMessage message) {
        return message.getId() != null && message.getName() != null && !message.getName().isEmpty() && message.getDescription() != null && !message.getDescription().isEmpty() && message.getAvailability() != null && message.getPrice() != null && message.getPrice().compareTo(BigDecimal.ZERO) > 0;
    }
}