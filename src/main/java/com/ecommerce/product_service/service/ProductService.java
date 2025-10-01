package com.ecommerce.product_service.service;

import com.ecommerce.product_service.core.ApiResponse;
import com.ecommerce.product_service.dto.request.ProductRequestDto;
import com.ecommerce.product_service.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ApiResponse<List<ProductResponseDto>> getAllProducts();
    ApiResponse<ProductResponseDto> createProduct(ProductRequestDto request);
    ApiResponse<ProductResponseDto> updateProduct(Long id, ProductRequestDto request);
    ApiResponse<?> deleteProduct(Long id);
}