package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductUpdateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.product.ProductResponse;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.mapper.ProductMapper;
import com.melnur.AdisyonTakipSistemi.repository.IProductRepository;
import com.melnur.AdisyonTakipSistemi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final IProductRepository _IProductRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponse createProduct(ProductCreateRequest request){

        ProductEntity product = productMapper.toEntity(request);

        ProductEntity saved = _IProductRepository.save(product);

        return productMapper.toResponse(saved);
    }


    @Transactional
    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request){
        ProductEntity product = _IProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        if (request.getName() != null)
            product.setName(request.getName());

        if (request.getSalePrice() != null)
            product.setSalePrice(request.getSalePrice());

        if (request.getCostPrice() != null)
            product.setCostPrice(request.getCostPrice());

        ProductEntity updated = _IProductRepository.save(product);
        return productMapper.toResponse(updated);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id){
        ProductEntity product = _IProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ürün bulunamadı"));
        _IProductRepository.delete(product);
    }

    @Override
    public ProductResponse getProductById(Long id){
        ProductEntity product = _IProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ürün bulunamadı"));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts(){
        return _IProductRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }


    @Override
    public List<ProductResponse> getProductsByCategory(String category) {

        return _IProductRepository.findByCategory(category)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }


}