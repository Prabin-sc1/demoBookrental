package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.exception.CategoryAlreadyExistsException;
import com.bookrental.bookrental.exception.ResourceNotFoundException;
import com.bookrental.bookrental.mapper.CategoryMapper;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;

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
            throw new AppException(e.getMessage());
        }
        category.setActive(true);
        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("A category with the name " + category.getName() + " already exists. ");
        }
    }

    @Override
    public CategoryResponsePojo getCategoryById(Integer id) {
//        Category c = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " Id ", id));
//        return this.modelMapper.map(c, CategoryResponsePojo.class);
        return categoryMapper.getSingleCategory(id);
    }

    @Override
    public List<CategoryResponsePojo> getAllCategory() {
        return this.categoryMapper.getAllCategory();
    }

    @Override
    public void deleteCategory(Integer id) {
        this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        this.categoryRepository.deleteById(id);
    }
}
