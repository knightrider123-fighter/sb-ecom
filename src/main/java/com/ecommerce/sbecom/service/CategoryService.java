package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryDto;
import com.ecommerce.sbecom.payload.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    //Lose coupling
    CategoryResponse getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);

    ResponseEntity<?> deleteCatoryI(long categoryId);
}
