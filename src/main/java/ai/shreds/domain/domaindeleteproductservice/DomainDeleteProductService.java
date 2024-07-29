package ai.shreds.domain;

import ai.shreds.shared.SharedDeleteProductResponseDTO;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DomainDeleteProductService implements DomainProductServicePort {

    private final DomainProductRepositoryPort repository;

    @Override
    public SharedDeleteProductResponseDTO deleteProduct(UUID id) {
        return repository.findById(id).map(product -> {
            repository.deleteById(id);
            log.info("Product with ID {} deleted successfully.", id);
            return new SharedDeleteProductResponseDTO("Operation successful");
        }).orElseThrow(() -> {
            log.error("Product with ID {} not found.", id);
            return new ProductNotFoundException("Product not found");
        });
    }

    private static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}