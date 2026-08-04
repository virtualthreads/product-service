package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductNameIgnoreCase(String productName);
    boolean existsByProductNameIgnoreCaseAndProductIdNot(String productName, Long productId);

    List<Product> findByCategory_categoryId(Long categoryId);
    List<Product> findByBrandIgnoreCase(String brand);

    @Query("""
        SELECT p FROM Product p
            JOIN FETCH p.variants pv
                LEFT JOIN FETCH p.category pc
                    WHERE ( :keyword IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) )
                        AND (:brand IS NULL OR LOWER(p.brand) = LOWER(:brand))
                        AND (:minPrice IS NULL OR pv.price >= :minPrice)
                        AND (:maxPrice IS NULL OR pv.price <= :maxPrice)
    """)
    List<Product> searchProduct(
            @Param("keyword") String keyword,
            @Param("brand") String brand,
            @Param("color") String color,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
}