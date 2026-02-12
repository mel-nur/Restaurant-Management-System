package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductUpdateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.product.ProductResponse;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductCreateRequest request);

    ProductResponse updateProduct(Long id, ProductCreateRequest request);

    ProductResponse updateProduct(Long id, ProductUpdateRequest request);

    void deleteProduct(Long id);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getProductsByCategory(Long categoryId);
}
