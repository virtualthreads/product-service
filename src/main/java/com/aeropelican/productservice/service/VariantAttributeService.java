package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeCreateRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.VariantAttributeResponseDTO;
import com.aeropelican.productservice.entity.VariantAttribute;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.VariantAttributeMapper;
import com.aeropelican.productservice.repository.VariantAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantAttributeService {

    private final VariantAttributeRepository variantAttributeRepository;
    private final VariantAttributeMapper variantAttributeMapper;

    /**
     * List All Variant Attributes
     */
    public PageResponse<VariantAttributeResponseDTO> listVariantAttributes(PageRequestDTO requestDTO) {

        Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                ? Sort.by(requestDTO.getSortBy()).descending()
                : Sort.by(requestDTO.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                requestDTO.getPage(),
                requestDTO.getSize(),
                sort
        );

        Page<VariantAttribute> page = variantAttributeRepository.findAll(pageable);

        List<VariantAttributeResponseDTO> response = page.getContent()
                .stream()
                .map(variantAttributeMapper::toResponseDTO)
                .toList();

        return PageResponseMapper.toPageResponse(page, response);
    }

    /**
     * Get Variant Attribute By Id
     */
    public VariantAttributeResponseDTO getVariantAttribute(Long id) {

        VariantAttribute attribute = variantAttributeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Variant Attribute",
                                String.valueOf(id)));

        return variantAttributeMapper.toResponseDTO(attribute);
    }

    /**
     * Create Variant Attribute
     */
    public VariantAttributeResponseDTO createVariantAttribute(
            VariantAttributeCreateRequestDTO request) {

        if (variantAttributeRepository.existsByVariantIdAndAttrName(
                request.getVariantId(),
                request.getAttrName())) {

            throw new BadRequestException(
                    "Attribute already exists for this Variant.");
        }

        VariantAttribute attribute =
                variantAttributeMapper.toEntity(request);

        VariantAttribute saved =
                variantAttributeRepository.save(attribute);

        return variantAttributeMapper.toResponseDTO(saved);
    }

    /**
     * Update Variant Attribute
     */
    public VariantAttributeResponseDTO updateVariantAttribute(
            Long id,
            VariantAttributeUpdateRequestDTO request) {

        VariantAttribute attribute = variantAttributeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Variant Attribute",
                                String.valueOf(id)));

        attribute.setVariantId(request.getVariantId());
        attribute.setAttrName(request.getAttrName());
        attribute.setAttrValue(request.getAttrValue());

        VariantAttribute updated = variantAttributeRepository.save(attribute);

        return variantAttributeMapper.toResponseDTO(updated);
    }

    /**
     * Delete Variant Attribute
     */
    public VariantAttribute deleteVariantAttribute(Long id) {

        VariantAttribute attribute = variantAttributeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Variant Attribute",
                                String.valueOf(id)));

        variantAttributeRepository.delete(attribute);

        return attribute;
    }

}