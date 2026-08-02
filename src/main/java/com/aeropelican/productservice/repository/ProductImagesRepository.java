package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product_Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository <Product_Images, Long>{
}
