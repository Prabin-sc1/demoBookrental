package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.Exception.AppException;
import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.mapper.CategoryMapper;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
//
//    @Autowired
//    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;
    private CustomMessageSource customMessageSource;
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
    public Category getCategoryById(Integer id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new AppException(customMessageSource.
                get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.CATEGORY)));
    }

    @Override
    public List<Category> getAllCategory() {
//        return this.categoryMapper.getAllCategory();
        return this.categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteById(id);
    }
}
