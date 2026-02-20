package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);


}
