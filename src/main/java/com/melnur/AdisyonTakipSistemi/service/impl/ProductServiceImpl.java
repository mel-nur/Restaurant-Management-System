package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.repository.IProductRepository;
import com.melnur.AdisyonTakipSistemi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final IProductRepository _IProductRepository;

    @Override
    public ProductEntity addProduct(ProductEntity product){
        return _IProductRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAllProducts(){
        return _IProductRepository.findAll();
    }
}
