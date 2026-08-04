package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "variant_attributes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_attribute_id", nullable = false)
    private Long id;

    @Column(name = "variant_id")
    private Long variantId;

    @Size(max = 50)
    @NotNull
    @Column(name = "attr_name", nullable = false, length = 50)
    private String attrName;

    @Size(max = 100)
    @NotNull
    @Column(name = "attr_value", nullable = false, length = 100)
    private String attrValue;


}