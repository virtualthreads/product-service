package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.VariantAttributeCreateRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.VariantAttributeResponseDTO;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.entity.VariantAttribute;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.VariantAttributeMapper;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import com.aeropelican.productservice.repository.VariantAttributeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VariantAttributeService {

    private final VariantAttributeRepository attributeRepository;
    private final ProductVariantsRepository variantsRepository;
    private final VariantAttributeMapper mapper;

    public VariantAttributeService(VariantAttributeRepository attributeRepository,
                                   ProductVariantsRepository variantsRepository,
                                   VariantAttributeMapper mapper) {
        this.attributeRepository = attributeRepository;
        this.variantsRepository = variantsRepository;
        this.mapper = mapper;
    }

    public VariantAttributeResponseDTO createAttribute(VariantAttributeCreateRequestDTO request) {
        ProductVariants variant = variantsRepository.findById(request.variantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product Variant not found with ID: " + request.variantId()));

        VariantAttribute attribute = mapper.toEntity(request, variant);
        VariantAttribute saved = attributeRepository.save(attribute);
        return mapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public VariantAttributeResponseDTO getAttributeById(Long id) {
        VariantAttribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Variant Attribute not found with ID: " + id));
        return mapper.toDTO(attribute);
    }

    @Transactional(readOnly = true)
    public List<VariantAttributeResponseDTO> getAttributesByVariantId(Long variantId) {
        if (!variantsRepository.existsById(variantId)) {
            throw new ResourceNotFoundException("Product Variant not found with ID: " + variantId);
        }
        return attributeRepository.findByProductVariant_VariantId(variantId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public VariantAttributeResponseDTO updateAttribute(Long id, VariantAttributeUpdateRequestDTO request) {
        VariantAttribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Variant Attribute not found with ID: " + id));

        attribute.setAttrName(request.attrName());
        attribute.setAttrValue(request.attrValue());

        VariantAttribute updated = attributeRepository.save(attribute);
        return mapper.toDTO(updated);
    }

    public void deleteAttribute(Long id) {
        if (!attributeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Variant Attribute not found with ID: " + id);
        }
        attributeRepository.deleteById(id);
    }
}