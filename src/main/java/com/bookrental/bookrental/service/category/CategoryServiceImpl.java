package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.mapper.CategoryMapper;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    private final CustomMessageSource customMessageSource;

    @Override
    public void createUpdateCateogory(CategoryRequestPojo crp) {
        Category category = new Category();
        if (crp.getId() != null) {
            category = categoryRepository.findById(crp.getId()).orElse(category);
        }
        try {
            beanUtils.copyProperties(category, crp);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }

        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(customMessageSource.get(Message.ALREADY_EXISTS.getCode(), ModuleNameConstants.CATEGORY));
        }
    }

    @Override
    public CategoryResponsePojo getCategoryById(Integer id) {
        return categoryMapper.getSingleCategory(id).orElseThrow(() ->
                new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.CATEGORY)));
    }

    @Override
    public List<CategoryResponsePojo> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.CATEGORY)));
    }
}
