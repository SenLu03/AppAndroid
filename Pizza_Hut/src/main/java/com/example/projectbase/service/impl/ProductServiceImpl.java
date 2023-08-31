package com.example.projectbase.service.impl;

import com.example.projectbase.converter.ProductConverter;
import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.domain.dto.request.ProductPointDTO;
import com.example.projectbase.domain.dto.request.ProductSearchPizzaDTO;
import com.example.projectbase.domain.dto.response.ProductResponseDTO;
import com.example.projectbase.domain.entity.*;
import com.example.projectbase.exception.AlreadyExistsException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.*;
import com.example.projectbase.service.ProductDetailService;
import com.example.projectbase.service.ProductService;
import com.example.projectbase.service.UserService;
import com.example.projectbase.util.BindingResultUtils;
import com.example.projectbase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public ResponseEntity<?> findOne(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw new NotFoundException("id là " + id + " không tồn tại");
        }
        return ResponseEntity.ok(productConverter.converEntityToDTO(productEntity.get()));
    }

    @Override
    public List<ProductResponseDTO> findWithPizza(ProductSearchPizzaDTO productSearchPizzaDTO){
        List<ProductEntity> productList = productRepository.findByCategoryId(productSearchPizzaDTO.getCategoryId());
        if (productSearchPizzaDTO.getCakeSize() == null && productSearchPizzaDTO.getCakeBase() == null) {
            return productConverter.converListEntityToListDTO(productList);
        }
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (ProductEntity item : productList){
            ProductResponseDTO productResponseDTO = productConverter.converEntityToDTO(item);
            if(item.getId() == productSearchPizzaDTO.getId()){
                if(productSearchPizzaDTO.getCakeBase()!="string"&&productSearchPizzaDTO.getCakeBase()!=null){
                    productResponseDTO.setCakeBase(productSearchPizzaDTO.getCakeBase());
                }
                if(productSearchPizzaDTO.getCakeSizeId()!=0&&productSearchPizzaDTO.getCakeSizeId()!=null){
                    SizeEntity sizeEntity=sizeRepository.findById(productSearchPizzaDTO.getCakeSizeId()).get();;
                    productResponseDTO.setCakeSize(sizeEntity.getName());
                    productResponseDTO.setPrice((item.getPrice()+sizeEntity.getPrice())+"");
                }
            }
            //neu khong co thi gan mac dinh
            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }

    @Override
    public List<ProductResponseDTO> findByCombo(Long comboId, Long categoryId) {
        return productConverter.converListEntityToListDTO(productRepository.findByCombo(comboId,categoryId));
    }

    @Override
    public ResponseEntity<?> createProduct(@Valid ProductCreateDTO productDTO,
                                           BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(productDTO.getCategoryId());
        if(optionalCategoryEntity.isPresent()){
            ProductEntity productEntity = productConverter.converDTOToEntity(productDTO);
            return ResponseEntity.ok(productConverter.converEntityToDTO(productRepository.save(productEntity)));
        }
        else {
            throw new AlreadyExistsException("Category already exists with id = " + productDTO.getCategoryId());
        }
    }

    @Override
    public ResponseEntity<?> updateProduct( ProductCreateDTO productDTO, BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productDTO.getId());
        if(optionalProductEntity.isPresent()){
            ProductEntity existPhoneEntity = optionalProductEntity.get();
            Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(existPhoneEntity.getCategoryEntity().getId());
            if(optionalCategoryEntity.isPresent()){
                existPhoneEntity = productConverter.converDTOToEntity(productDTO);
                ProductEntity productEntity = productRepository.save(existPhoneEntity);
                return  ResponseEntity.ok(productConverter.converEntityToDTO(productEntity));
            }
            else {
                throw new NotFoundException("Category not found with id = " + existPhoneEntity.getCategoryEntity().getId());
            }
        }
        else{
            throw new NotFoundException("Phone not found with id = " + productDTO.getId());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw new NotFoundException("không tìm thấy sản phẩm với id là " + id);
        }
        //check khoas ngoaij
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> findProductHavePoint(){
        List<ProductEntity> productEntities = productRepository.findProductHavePoint();
        List<ProductResponseDTO> productResponseDTOS = productConverter.converListEntityToListDTO(productEntities);
        return productResponseDTOS;
    }

    @Override
    public void changePoint(Long id){
        UserEntity userEntity = userService.getCurrentUser();
        CartEntity cartEntity=userEntity.getCartEntity();
        ProductEntity productEntity =productRepository.findById(id).orElseThrow(() -> new NullPointerException("không tìm thấy"));

        if(userEntity.getPoint() < productEntity.getPoint()||productEntity.getPoint()==0){
//            throw new NotFoundException()
//              bắn ra exception
            throw new NullPointerException("không đủ điểm!");
        }
        else{
            //add vào cart
            userEntity.setPoint(userEntity.getPoint()-productEntity.getPoint());
            userRepository.save(userEntity);
            ProductDetailEntity productDetailEntity=new ProductDetailEntity();
            productDetailEntity.setProductEntity(productEntity);
            productDetailEntity.setCartEntity(cartEntity);
            productDetailEntity.setPrice(0L);
            productDetailService.addToCart(productDetailRepository.save(productDetailEntity).getId());
        }
    }
}
