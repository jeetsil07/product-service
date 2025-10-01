package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.core.ApiResponse;
import com.ecommerce.product_service.dto.request.ProductRequestDto;
import com.ecommerce.product_service.dto.response.ProductResponseDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ApiResponse<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return ApiResponse.<List<ProductResponseDto>>builder()
                .status(HttpStatus.OK.value())
                .message("Products retrieved successfully")
                .data(products)
                .build();
    }

    @Override
    public ApiResponse<ProductResponseDto> createProduct(ProductRequestDto request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        Product savedProduct = productRepository.save(product);

        return ApiResponse.<ProductResponseDto>builder()
                .status(HttpStatus.CREATED.value())
                .message("Product Created Successfully")
                .data(mapToDTO(savedProduct))
                .build();
    }

    @Override
    public ApiResponse<ProductResponseDto> updateProduct(Long id, ProductRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        Product savedProduct = productRepository.save(product);

        return ApiResponse.<ProductResponseDto>builder()
                .status(HttpStatus.OK.value())
                .message("Product Updated Successfully")
                .data(mapToDTO(savedProduct))
                .build();
    }

    @Override
    public ApiResponse<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("product deleted"+id)
                .data(null)
                .build();
    }

    private ProductResponseDto mapToDTO(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
