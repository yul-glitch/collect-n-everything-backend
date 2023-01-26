package com.diy.api;


import com.diy.generated.api.ProductApi;
import com.diy.generated.model.ProductDto;
import com.diy.generated.model.ProductWithoutCategoryDto;
import com.diy.mapper.ProductModelMapper;
import com.diy.model.ProductModel;
import com.diy.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductApiImpl implements ProductApi {

    ProductService productService;
    ProductModelMapper productModelMapper;

    @Override
    public ResponseEntity<String> deleteProductById(Long productid) {
        return ResponseEntity.ok(productService.deleteProductById(productid));
    }

    @Override
    public ResponseEntity<ProductWithoutCategoryDto> findProductById(Long productid) {
        return ResponseEntity.ok(productModelMapper.toProductWOCategoryDto(productService.findProductById(productid)));
    }



    @Override
    public ResponseEntity<List<ProductWithoutCategoryDto>> getAllProductByStoreId(Long storeid) {
        return ResponseEntity.ok(productModelMapper.toProductWOCategoryDto(productService.getAllProductByStoreId(storeid)));
    }

    @Override
    public ResponseEntity<ProductWithoutCategoryDto> saveProduct(ProductDto productDto) {
        ProductModel product = productModelMapper.dtoToModel(productDto);
        return ResponseEntity.ok(productModelMapper.toProductWOCategoryDto(productService.saveProduct(product)));
    }

}
