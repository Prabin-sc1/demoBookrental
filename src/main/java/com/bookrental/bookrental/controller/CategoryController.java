package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController extends MyBaseController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.module = ModuleNameConstants.CATEGORY;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequestPojo categoryRequestPojo) {
        categoryService.createUpdateCateogory(categoryRequestPojo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GlobalApiResponse> getAllCategory() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIVE_ALL.getCode(), module), categoryService.getAllCategory()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), categoryService.getCategoryById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.DELETE.getCode(), module), null));
    }
}
