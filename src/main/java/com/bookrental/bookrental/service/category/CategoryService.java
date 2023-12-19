package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;

import java.util.List;

public interface CategoryService {
    void createUpdateCateogory(CategoryRequestPojo crp);

    CategoryRequestPojo getCategoryById(Integer id);

    List<CategoryResponsePojo> getAllCategory();

    void deleteCategory(Integer id);
}
