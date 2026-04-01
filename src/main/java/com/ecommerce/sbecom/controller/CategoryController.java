package com.ecommerce.sbecom.controller;


import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryResponse;
import com.ecommerce.sbecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController( CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping("api/public/categories")
    public ResponseEntity<CategoryResponse>  getAllCategories(){
        CategoryResponse categoryResponse=categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("api/public/categories")
    public List<Category> addCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return null ;
    }

    @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable long categoryId ){
        return categoryService.deleteCatoryI(categoryId);
    }
}
