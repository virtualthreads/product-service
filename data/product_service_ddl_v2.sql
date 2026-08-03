-- =====================================================================
-- PRODUCT SERVICE - DDL SCRIPT v2 (MySQL)
-- Adds support for product VARIANTS (e.g. iPhone 16 Pro in multiple
-- colors / storage sizes) without breaking the "basic app" column cap.
--
-- Change from v1:
--   - products        : now the "concept" product only (no price/sku)
--   - product_variants: NEW table — the actual purchasable item
--                        (sku, color, storage, price live here)
--   - product_images   : now hangs off variant_id, not product_id,
--                        since each color needs its own photos
--
-- Inventory Service should track stock against variant_id (not
-- product_id) — that's the actual sellable unit.
-- =====================================================================

CREATE DATABASE IF NOT EXISTS ecom_product;
USE ecom_product;

DROP TABLE IF EXISTS product_images;
DROP TABLE IF EXISTS product_variants;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

-- ---------------------------------------------------------------------
-- Table 1: categories  (unchanged from v1)
-- ---------------------------------------------------------------------
CREATE TABLE categories (
    category_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name       VARCHAR(100)  NOT NULL,
    description         VARCHAR(500)  NULL,
    parent_category_id  BIGINT        NULL,
    is_active           BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
                                       ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_category_id) REFERENCES categories(category_id)
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_categories_parent ON categories(parent_category_id);

-- ---------------------------------------------------------------------
-- Table 2: products  (the "concept" product — no price/sku anymore)
-- ---------------------------------------------------------------------
CREATE TABLE products (
    product_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id     BIGINT         NOT NULL,
    product_name    VARCHAR(150)   NOT NULL,
    description     TEXT           NULL,
    brand           VARCHAR(100)   NULL,
    is_active       BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
                                    ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id) REFERENCES categories(category_id)
        ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_products_category ON products(category_id);
CREATE INDEX idx_products_name ON products(product_name);
CREATE INDEX idx_products_active ON products(is_active);

-- ---------------------------------------------------------------------
-- Table 3: product_variants  (NEW — the actual purchasable item)
-- color / storage_capacity are NULL for products that don't need
-- variation (a book, a toy) and populated for ones that do
-- (a phone: color + storage).
-- ---------------------------------------------------------------------
CREATE TABLE product_variants (
    variant_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id        BIGINT         NOT NULL,
    sku               VARCHAR(50)    NOT NULL UNIQUE,
    color             VARCHAR(50)    NULL,
    storage_capacity  VARCHAR(20)    NULL,
    price             DECIMAL(10,2)  NOT NULL,
    is_active         BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at         TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
                                       ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_variant_product
        FOREIGN KEY (product_id) REFERENCES products(product_id)
        ON DELETE CASCADE,
    CONSTRAINT chk_variant_price CHECK (price >= 0),
    CONSTRAINT uq_variant_combo UNIQUE (product_id, color, storage_capacity)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_variants_product ON product_variants(product_id);
CREATE INDEX idx_variants_active ON product_variants(is_active);

-- ---------------------------------------------------------------------
-- Table 4: product_images  (now hangs off variant_id, not product_id)
-- ---------------------------------------------------------------------
CREATE TABLE product_images (
    image_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    variant_id      BIGINT         NOT NULL,
    image_url       VARCHAR(255)   NOT NULL,
    is_primary      BOOLEAN        NOT NULL DEFAULT FALSE,
    display_order   INT            NOT NULL DEFAULT 0,
    created_at      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_image_variant
        FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_images_variant ON product_images(variant_id);
