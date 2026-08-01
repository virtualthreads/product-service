CREATE TABLE variant_attributes (
                                    variant_attribute_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    variant_id   BIGINT NOT NULL,
                                    attr_name    VARCHAR(50) NOT NULL,   -- 'color', 'size', 'material'
                                    attr_value   VARCHAR(100) NOT NULL,  -- 'Black Titanium', 'XL', 'Cotton'
                                    CONSTRAINT fk_va_variant FOREIGN KEY (variant_id)
                                        REFERENCES product_variants(variant_id) ON DELETE CASCADE,
                                    UNIQUE KEY uq_variant_attr (variant_id, attr_name)
) ENGINE=InnoDB;
CREATE INDEX idx_va_lookup ON variant_attributes(attr_name, attr_value);