package com.example.projectbase.service.impl;

import com.example.projectbase.converter.CategoryConverter;
import com.example.projectbase.domain.dto.request.CategoryCreateDTO;
import com.example.projectbase.domain.dto.response.CategoryResponseDTO;
import com.example.projectbase.domain.entity.CategoryEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.exception.AlreadyExistsException;
import com.example.projectbase.exception.ForeignKeyConstraintException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CategoryRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.service.CategoryService;
import com.example.projectbase.util.BindingResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Autowired
    ProductRepository productRepository;


    @Override
    public ResponseEntity<?> createCategory(@Valid CategoryCreateDTO categoryDTO,
                                           BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        List<CategoryEntity> optionalCategoryEntity = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(optionalCategoryEntity == null || optionalCategoryEntity.isEmpty()){
            CategoryEntity categoryEntity = categoryConverter.converDTOToEntity(categoryDTO);
            return ResponseEntity.ok(categoryConverter.converEntityToDTO(categoryRepository.save(categoryEntity)));
        }
        else {
            throw new AlreadyExistsException("Category already exists with name " + categoryDTO.getCategoryName());
        }
    }

    @Override
    public List <CategoryResponseDTO> findAll(){
//        if (page == null || page < 0 || size == null || size < 0) {
//            return ResponseEntity.ok(categoryConverter.converListEntityToListDTO(categoryRepository.findAll()));
//        }
//        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(PageRequest.of(page, size));
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryConverter.converListEntityToListDTOResponse(categoryEntityList);
    }

    @Override
    public ResponseEntity<?> findOne(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(!categoryEntity.isPresent()){
            throw new NotFoundException("id là " + id + " không tồn tại");
        }
        return ResponseEntity.ok(categoryConverter.converEntityToDTO(categoryEntity.get()));
    }

    @Override
    public ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct) {
        List<CategoryEntity> categoryEntities = categoryRepository.findByCategoryName(nameProduct);
        if(categoryEntities == null || categoryEntities.isEmpty()){
            throw new NotFoundException("name là " + nameProduct + " không tồn tại");
        }
        if (page == null || page < 0 || size == null || size < 0) {
            return ResponseEntity.ok(categoryConverter.converListEntityToListDTO(categoryEntities));
        }
        List<CategoryEntity> categoryEntityList = categoryRepository.searchByName(nameProduct, PageRequest.of(page, size));
        return ResponseEntity.ok(categoryConverter.converListEntityToListDTO(categoryEntityList));

    }

    @Override
    public ResponseEntity<?> updateCategory(Long id, CategoryCreateDTO categoryDTO, BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if(optionalCategoryEntity.isPresent()){
            CategoryEntity existCategoryEntity = optionalCategoryEntity.get();
            existCategoryEntity = categoryConverter.converDTOToEntity(categoryDTO);
            existCategoryEntity.setId(id);
            CategoryEntity categoryEntity = categoryRepository.save(existCategoryEntity);
            return  ResponseEntity.ok(categoryConverter.converEntityToDTO(categoryEntity));
        }
        else{
            throw new NotFoundException(" Category not found with id = " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(!categoryEntity.isPresent()){
            throw new NotFoundException("không tìm thấy sản phẩm với id là " + id);
        }
        Set<ProductEntity> productEntities = categoryEntity.get().getProductEntities();
        if(productEntities != null || !productEntities.isEmpty()){
            throw new ForeignKeyConstraintException("Đang có product có khóa ngoại với id là " + id + " nên không thể xóa" );
        }
        categoryRepository.deleteById(id);
    }

}
