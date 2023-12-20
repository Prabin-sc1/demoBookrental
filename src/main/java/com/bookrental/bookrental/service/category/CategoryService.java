package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;

import java.util.List;

public interface CategoryService {
    void createUpdateCateogory(CategoryRequestPojo crp);

    Category getCategoryById(Integer id);

    List<Category> getAllCategory();

    void deleteCategory(Integer id);
}
