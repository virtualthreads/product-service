package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product_Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_VariantsRepository extends JpaRepository<Product_Variants, Integer> {

}