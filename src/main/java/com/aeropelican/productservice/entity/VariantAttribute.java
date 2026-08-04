package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variant_attributes",
        uniqueConstraints = @UniqueConstraint(name = "uq_variant_attr", columnNames = {"variant_id", "attr_name"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_attribute_id")
    private Long variantAttributeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_va_variant"))
    private ProductVariants productVariant;

    @Column(name = "attr_name", nullable = false, length = 50)
    private String attrName;

    @Column(name = "attr_value", nullable = false, length = 100)
    private String attrValue;
}