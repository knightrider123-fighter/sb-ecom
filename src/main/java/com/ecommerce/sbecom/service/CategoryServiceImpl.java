package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.exception.ApiException;
import com.ecommerce.sbecom.exception.ResourseNotFoundException;
import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryDto;
import com.ecommerce.sbecom.payload.CategoryResponse;
import com.ecommerce.sbecom.repo.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

//    private long id=1L;
    public CategoryRepository categoryRepository;

    public ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,ModelMapper modelMapper){

        this.categoryRepository=categoryRepository;
        this.modelMapper=modelMapper;

    }
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new ResourseNotFoundException("No Category added till now ");
        }
        List<CategoryDto> categoryDtoList=categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).toList();
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setContent(categoryDtoList);
        return categoryResponse;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

//        category.setCategoryId(id++);
        Category categoryToFind=categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if(categoryToFind!=null){
            throw new ApiException("Category with name already exist in DB with as "+category.toString());
        }
        Category category=modelMapper.map(categoryDto,Category.class);
        Category category1=categoryRepository.save(category);

        return null;
    }

    @Override
    public ResponseEntity<?> deleteCatoryI(long categoryId) {

        List<Category> findAllCategories=categoryRepository.findAll();
        Category category =categoryRepository.findById(categoryId).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Given id category not founded")
        );
        //Using stream API
//        Optional<Category> categoryOptional=findAllCategories.stream().
//                filter(c->c.getCategoryId().
//                        equals(categoryId)).findFirst();
//        if(categoryOptional.isEmpty()){
//            return new ResponseEntity<>("Given Id not found",HttpStatus.NOT_FOUND ) ;
//        }
//        findAllCategories.remove(categoryOptional.get());/

        // using repository
        categoryRepository.delete(category);
        return new ResponseEntity<>("Given Id dounded and deleted",HttpStatus.NOT_FOUND ) ;
    }


}
