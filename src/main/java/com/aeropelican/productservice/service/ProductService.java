package com.aeropelican.productservice.service;

<<<<<<< HEAD
import com.aeropelican.productservice.dto.request.ProductRequest;
import com.aeropelican.productservice.dto.request.ProductResponse;
=======
import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.entity.Category;
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ProductNotFound;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

<<<<<<< HEAD
    public ProductResponse createProduct(ProductRequest request) {
        if (request.categoryId() != null && !categoryRepository.existsById(request.categoryId())) {
            throw new ResourceNotFoundException("Category", String.valueOf(request.categoryId()));
        }
        if (productRepository.existsByProductNameIgnoreCase(request.productName())) {
            throw new BadRequestException("Product '%s' already exists".formatted(request.productName()));
        }
        Product product = productMapper.toEntity(request);
        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse getProduct(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(productId)));
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", String.valueOf(categoryId));
        }
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public List<ProductResponse> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(productId)));
        productRepository.delete(product);
    }
}
=======
        public PageResponse<ProductResponseDTO> listProducts(PageRequestDTO requestDTO) {
            Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                    ? Sort.by(requestDTO.getSortBy()).descending()
                    : Sort.by(requestDTO.getSortBy()).ascending();

            Pageable pageable = PageRequest.of(requestDTO.getPage(),requestDTO.getSize(),sort);

            Page<Product> pageResult = productRepository.findAll(pageable);

            List<ProductResponseDTO> content = pageResult.getContent()
                    .stream()
                    .map(ProductMapper::toResponseDTO)
                    .toList();

            return PageResponseMapper.toPageResponse(pageResult, content);
        }

    public ProductResponseDTO getProduct(Integer productId) {
            System.out.println("Attempting to fetch product with ID: " + productId);
            return productRepository.findById(productId)
                    .map(ProductMapper::toResponseDTO)
                    .orElseThrow(() -> new ResourceNotFoundException("Product is not found for provided ID: " + productId));
        }
        public ProductResponseDTO createProduct(ProductCreateRequestDTO request) {

            String productName = request.getProductName().trim();

            if (productRepository.existsByProductNameIgnoreCase(productName)) {
                throw new BadRequestException("Product already exists.");
            }

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Category", String.valueOf(request.getCategoryId()))
                    );

            Product product = ProductMapper.toEntity(request);
            //TODO: set category ID if present
            //product.setCategoryId(request.getCategoryId());
            return ProductMapper.toResponseDTO(productRepository.save(product));
        }

        public ProductResponseDTO updateProduct(Integer id, ProductUpdateRequestDTO request) {

            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(id)));

            String productName = request.getProductName().trim();

            if (productRepository.existsByProductNameIgnoreCaseAndProductIdNot(productName, id)) {
                throw new BadRequestException("Product name already exists.");
            }

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Category", String.valueOf(request.getCategoryId()))
                    );

            //TODO: Set category ID if present
            //product.setCategory(category);
            product.setProductName(productName);
            product.setDescription(request.getDescription());
            product.setBrand(request.getBrand());

            if (request.getIsActive() != null) {
                product.setIsActive(request.getIsActive());
            }

            return ProductMapper.toResponseDTO(productRepository.save(product));
        }

        //To delete a product
        public Product deleteProduct(Integer productId) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFound("Product not found"));
            productRepository.delete(product);
            return product;
        }
    }
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
