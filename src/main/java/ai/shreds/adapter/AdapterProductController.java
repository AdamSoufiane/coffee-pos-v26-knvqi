package ai.shreds.adapter;

import ai.shreds.application.ApplicationProductSearchServiceInputPort;
import ai.shreds.adapter.AdapterProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class AdapterProductController {

    private final ApplicationProductSearchServiceInputPort productSearchService;

    @GetMapping("/search/name")
    public ResponseEntity<List<AdapterProductResponseDTO>> searchProductsByName(@RequestParam String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name parameter is required");
        }
        List<AdapterProductResponseDTO> products = productSearchService.searchByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<AdapterProductResponseDTO>> searchProductsByDescription(@RequestParam String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description parameter is required");
        }
        List<AdapterProductResponseDTO> products = productSearchService.searchByDescription(description);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<AdapterProductResponseDTO>> searchProductsByCategory(@RequestParam String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category parameter is required");
        }
        List<AdapterProductResponseDTO> products = productSearchService.searchByCategory(category);
        return ResponseEntity.ok(products);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + ex.getMessage());
    }
}