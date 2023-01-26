package com.diy.service;

import com.diy.entity.ProductEntity;
import com.diy.exception.ExceptionHandler;
import com.diy.mapper.CycleAvoidingMappingContext;
import com.diy.mapper.ProductModelMapper;
import com.diy.model.ProductModel;
import com.diy.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class ProductService {

    ProductModelMapper productModelMapper;
    ProductRepository productRepository;

    public String deleteProductById(Long productid) {
        try {
            ProductEntity productEntity = productRepository.findById(productid).orElseThrow(() ->
                    new ExceptionHandler("We could not find the product you want to delete"));
            productEntity.setUpdatedAt(LocalDateTime.now());
            productEntity.setDeletedAt(LocalDateTime.now());
            productRepository.save(productEntity);
            return "Your product has been deleted";
        } catch (Exception e) {
            log.error("Error while deleting a product: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your product");
        }
    }

    public ProductModel findProductById(Long productid) {
        try {
            ProductEntity productEntity = productRepository.findById(productid).orElseThrow(() ->
                    new ExceptionHandler("We could not find your product"));
            return productModelMapper.entityToModel(productEntity, new CycleAvoidingMappingContext());
        }  catch (Exception e) {
            log.error("Error while finding product by id: " + e.getMessage());
            throw new ExceptionHandler("We could not find your product");
        }
    }

    public List<ProductModel> getAllProductByStoreId(Long storeid) {
        try {
            List<ProductEntity> productEntity = productRepository.findAllByStoreId(storeid);
            return productModelMapper.entitiesToModels(productEntity, new CycleAvoidingMappingContext());
        }  catch (Exception e) {
            log.error("Error while finding products: " + e.getMessage());
            throw new ExceptionHandler("We could not find your products");
        }
    }

    public ProductModel saveProduct(ProductModel productModel) {
        if (productModel.getProductId() == null)
            return createProduct(productModel);
        else
            return updateProduct(productModel);
    }

    private ProductModel createProduct(ProductModel productModel) {
        try {
            ProductEntity productEntity = productModelMapper.modelToEntity(productModel);
            productEntity.setCreatedAt(LocalDateTime.now());
            productRepository.save(productEntity);
            return productModelMapper.entityToModel(productEntity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while creating a product: " + e.getMessage());
            log.error("Product model: " + productModel.toString());
            throw new ExceptionHandler("We could not create your product");
        }
    }

    private ProductModel updateProduct(ProductModel productModel) {
        try {
            ProductEntity productEntity = productRepository.findById(productModel.getProductId()).orElseThrow(() ->
                    new ExceptionHandler("We could not find your product"));
            productModelMapper.updateStoreFromModel(productModel, productEntity, new CycleAvoidingMappingContext());
            productEntity.setUpdatedAt(LocalDateTime.now());
            productRepository.save(productEntity);
            return productModelMapper.entityToModel(productEntity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while updating a product: " + e.getMessage());
            log.error("Product model: " + productModel.toString());
            throw new ExceptionHandler("We could not update your information");
        }
    }

}
