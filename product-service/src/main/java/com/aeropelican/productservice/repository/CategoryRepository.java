package com.aeropelican.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aeropelican.productservice.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    List<Category> findByParentCategoryIdIsNullAndIsActiveIsTrue();

    List<Category> findByParentCategoryId(Long parentCategoryId);
}
