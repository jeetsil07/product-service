package com.ecommerce.product_service.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {
    private int status;      // HTTP status code
    private String message;  // Success/Error message
    private T data;          // Actual payload
}
