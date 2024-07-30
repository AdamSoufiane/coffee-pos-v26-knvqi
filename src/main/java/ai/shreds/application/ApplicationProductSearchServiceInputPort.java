package ai.shreds.application;

import ai.shreds.adapter.AdapterProductResponseDTO;
import java.util.List;

/**
 * Interface for product search service in the application layer.
 * Provides methods to search products by name, description, and category.
 */
public interface ApplicationProductSearchServiceInputPort {
    /**
     * Searches for products by their name.
     * @param name the name of the product to search for
     * @return a list of AdapterProductResponseDTO objects matching the search criteria
     */
    List<AdapterProductResponseDTO> searchByName(String name);

    /**
     * Searches for products by their description.
     * @param description the description of the product to search for
     * @return a list of AdapterProductResponseDTO objects matching the search criteria
     */
    List<AdapterProductResponseDTO> searchByDescription(String description);

    /**
     * Searches for products by their category.
     * @param category the category of the product to search for
     * @return a list of AdapterProductResponseDTO objects matching the search criteria
     */
    List<AdapterProductResponseDTO> searchByCategory(String category);
}