package io.ermix.sbAppEc.service;

import io.ermix.sbAppEc.enums.ProductCategoryEnum;
import io.ermix.sbAppEc.model.Product;
import io.ermix.sbAppEc.repository.ProductRepository;
import io.ermix.sbAppEc.specification.ProductSpecification;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductSpecification productSpecification;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Create a new product
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Read a product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Update an existing product
    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategory(productDetails.getCategory());

        return productRepository.save(product);
    }

    // Delete a product
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    // Search products with dynamic filtering
    public Page<Product> searchProducts(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        ProductCategoryEnum category,
        Integer minStockQuantity,
        Pageable pageable
    ) {
        return productRepository.findAll(
            productSpecification.filterProducts(
                name,
                minPrice,
                maxPrice,
                category,
                minStockQuantity
            ),
            pageable
        );
    }
}
