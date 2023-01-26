package com.diy.api;

import com.diy.generated.api.CategoryApi;
import com.diy.generated.model.CategoryDto;
import com.diy.generated.model.CategoryWithProductDto;
import com.diy.generated.model.CategoryWithoutProductDto;
import com.diy.mapper.CategoryModelMapper;
import com.diy.service.CategoryService;
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
public class CategoryApiImpl implements CategoryApi {

    CategoryService categoryService;
    CategoryModelMapper categoryModelMapper;

    @Override
    public ResponseEntity<List<CategoryWithoutProductDto>> getAllCategoryByStoreId(Long storeid) {
        return ResponseEntity.ok(categoryModelMapper.modelsToCategoryWoProductDtos(categoryService.getAllCategoryByStoreId(storeid)));
    }

    @Override
    public ResponseEntity<CategoryWithProductDto> getCategoryAndItsProductByStoreId(Long storeid, Long categoryid) {
        return ResponseEntity.ok(categoryModelMapper.modelToCategoryWPDto(categoryService.getCategoryAndItsProductByStoreId(categoryid, storeid)));
    }

    @Override
    public ResponseEntity<CategoryWithoutProductDto> saveCategory(CategoryWithoutProductDto categoryWithoutProductDto) {
        return ResponseEntity.ok(categoryModelMapper.modelToCategoryWoPDto(
                categoryService.saveCategory(categoryModelMapper.categoryWithoutProductDtoToModel(categoryWithoutProductDto))));
    }

    @Override
    public ResponseEntity<String> deleteCategoryById(Long categoryid) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(categoryid));
    }
}
