package ai.shreds.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainInventoryItemAlreadyExistsException extends RuntimeException {
    public DomainInventoryItemAlreadyExistsException(String name) {
        super("Inventory item with name '" + name + "' already exists.");
    }
}