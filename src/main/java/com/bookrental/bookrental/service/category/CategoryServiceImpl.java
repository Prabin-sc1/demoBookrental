package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.Exception.AppException;
import com.bookrental.bookrental.mapper.CategoryMapper;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    @Override
    public void createUpdateCateogory(CategoryRequestPojo crp) {
        Category category = new Category();
        if (crp.getId() != null) {
            category = categoryRepository.findById(crp.getId()).orElse(category);
        }
        try {
            beanUtils.copyProperties(category, crp);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage(), e);
        }
        categoryRepository.save(category);
    }

    @Override
    public CategoryRequestPojo getCategoryById(Integer id) {
        return null;
    }

    @Override
    public List<CategoryResponsePojo> getAllCategory() {
        return this.categoryMapper.getAllCategory();
    }


    @Override
    public void deleteCategory(Integer id) {

    }
}
