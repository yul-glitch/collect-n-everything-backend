package com.diy.service;

import com.diy.entity.CategoryEntity;
import com.diy.exception.ExceptionHandler;
import com.diy.generated.model.CategoryWithProductDto;
import com.diy.generated.model.CategoryWithoutProductDto;
import com.diy.mapper.CategoryModelMapper;
import com.diy.mapper.CycleAvoidingMappingContext;
import com.diy.model.CategoryModel;
import com.diy.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryModelMapper categoryModelMapper;

    public List<CategoryModel> getAllCategoryByStoreId(Long storeid) {
        try {
            List<CategoryEntity> categoryEntities = categoryRepository.findAllByStoreId(storeid);
            return categoryModelMapper.entitiesToModels(categoryEntities, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while finding your category by categoryid: " + e.getMessage());
            throw new ExceptionHandler("We could not find your category");
        }
    }

    public CategoryModel getCategoryAndItsProductByStoreId(Long categoryid, Long storeid) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findByCategoryIdAndStoreId(categoryid, storeid).orElseThrow(() ->
                    new ExceptionHandler("We could not find your category"));
            return categoryModelMapper.entityToModel(categoryEntity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while finding your category by category id and store id: " + e.getMessage());
            throw new ExceptionHandler("We could not find your category");
        }

    }

    public CategoryModel saveCategory(CategoryModel categoryModel) {
        if (categoryModel.getCategoryId() == null)
            return createCategory(categoryModel);
        else
            return updateCategory(categoryModel);
    }

    private CategoryModel createCategory(CategoryModel categoryModel) {
        try {
            CategoryEntity categoryEntity = categoryModelMapper.modelToEntity(categoryModel);
            categoryEntity.setCreatedAt(LocalDateTime.now());
            categoryRepository.save(categoryEntity);
            return categoryModelMapper.entityToModel(categoryEntity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while creating a category: " + e.getMessage());
            log.error("Category model: " + categoryModel.toString());
            throw new ExceptionHandler("We could not create your product");
        }

    }

    private CategoryModel updateCategory(CategoryModel categoryModel) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findById(categoryModel.getCategoryId()).orElseThrow(() ->
                    new ExceptionHandler("We could not find your category"));
            categoryModelMapper.updateCategoryFromModel(categoryModel, categoryEntity, new CycleAvoidingMappingContext());
            categoryEntity.setUpdatedAt(LocalDateTime.now());
            return categoryModelMapper.entityToModel(categoryEntity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while updating a category: " + e.getMessage());
            log.error("Category model: " + categoryModel.toString());
            throw new ExceptionHandler("We could not update your product");
        }
    }

    public String deleteCategoryById(Long categoryid) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findById(categoryid).orElseThrow(() ->
                    new ExceptionHandler("We could not find your category"));
            categoryEntity.setUpdatedAt(LocalDateTime.now());
            categoryEntity.setDeletedAt(LocalDateTime.now());
            categoryRepository.save(categoryEntity);
            return "Your category has been deleted";
        } catch (Exception e) {
            log.error("Error while deleting a product: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your category");
        }
    }
}
