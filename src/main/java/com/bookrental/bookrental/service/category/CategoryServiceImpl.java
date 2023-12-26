package com.bookrental.bookrental.service.category;

import com.bookrental.bookrental.Exception.AppException;
import com.bookrental.bookrental.Exception.ResourceNotFoundException;
import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.mapper.CategoryMapper;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.category.CategoryRequestPojo;
import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;

    private final CategoryMapper categoryMapper;

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
    public CategoryResponsePojo getCategoryById(Integer id) {
//        return this.categoryRepository.findById(id).orElseThrow(() -> new AppException(customMessageSource.
//                get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.CATEGORY)));
        Category c = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " Id ", id));
        return this.modelMapper.map(c, CategoryResponsePojo.class);
    }

    @Override
    public List<CategoryResponsePojo> getAllCategory() {
        return this.categoryMapper.getAllCategory();
//        return this.categoryRepository.findAll();
//        List<Category> list = this.categoryRepository.findAll();
//        List<CategoryResponsePojo> list1 = list.stream().map(e -> this.modelMapper.map(e, CategoryResponsePojo.class)).collect(Collectors.toList());
//        return list1;
    }

    @Override
    public void deleteCategory(Integer id) {
        this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        this.categoryRepository.deleteById(id);
    }
}
