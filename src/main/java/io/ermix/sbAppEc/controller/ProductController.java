package io.ermix.sbAppEc.controller;

import io.ermix.sbAppEc.enums.ProductCategoryEnum;
import io.ermix.sbAppEc.model.Product;
import io.ermix.sbAppEc.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService
            .getProductById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
        @PathVariable Long id,
        @RequestBody Product productDetails
    ) {
        Product updatedProduct = productService.updateProduct(
            id,
            productDetails
        );
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice,
        @RequestParam(required = false) ProductCategoryEnum category,
        @RequestParam(required = false) Integer minStockQuantity,
        Pageable pageable
    ) {
        Page<Product> products = productService.searchProducts(
            name,
            minPrice,
            maxPrice,
            category,
            minStockQuantity,
            pageable
        );

        return ResponseEntity.ok(products);
    }
}
