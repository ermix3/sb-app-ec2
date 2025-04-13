package io.ermix.sbAppEc.specification;

import io.ermix.sbAppEc.enums.ProductCategoryEnum;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {

    public Specification<io.ermix.sbAppEc.model.Product> filterProducts(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        ProductCategoryEnum category,
        Integer minStockQuantity) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
                ));
            }

            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("price"), minPrice
                ));
            }

            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get("price"), maxPrice
                ));
            }

            if (category != null) {
                predicates.add(criteriaBuilder.equal(
                    root.get("category"), category
                ));
            }

            if (minStockQuantity != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("stockQuantity"), minStockQuantity
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
