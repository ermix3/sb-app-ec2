CREATE TABLE products
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)   NOT NULL,
    description    TEXT           NOT NULL,
    price          DECIMAL(19, 2) NOT NULL,
    stock_quantity INT,
    category       VARCHAR(50),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    INDEX          idx_product_name (name),
    INDEX          idx_product_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Add a comment to the table
ALTER TABLE products COMMENT = 'Stores product information including inventory and pricing data';
