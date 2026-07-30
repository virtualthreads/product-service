package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
    @Column(name = "is_active")
    private Boolean isActive = true;
    @Column(name = "created_at")
    private LocalDateTime createAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
