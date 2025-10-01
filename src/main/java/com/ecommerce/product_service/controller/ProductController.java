package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.core.ApiResponse;
import com.ecommerce.product_service.dto.request.ProductRequestDto;
import com.ecommerce.product_service.dto.response.ProductResponseDto;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/view")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> viewProducts() {
        ApiResponse<List<ProductResponseDto>> response = productService.getAllProducts();


        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductResponseDto>> addProduct(@RequestBody ProductRequestDto request) {
        ApiResponse<ProductResponseDto> response = productService.createProduct(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto request) {
        ApiResponse<ProductResponseDto> response = productService.updateProduct(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
