package ai.shreds.application;

import ai.shreds.shared.AdapterRealTimeUpdateRequest;
import ai.shreds.shared.AdapterRealTimeUpdateResponse;

/**
 * Interface for handling real-time updates of product data.
 */
@FunctionalInterface
public interface ApplicationRealTimeUpdateServicePort {
    /**
     * Processes the real-time update message.
     *
     * @param message the real-time update request message
     * @return the response after processing the real-time update
     */
    AdapterRealTimeUpdateResponse handleRealTimeUpdate(AdapterRealTimeUpdateRequest message);
}