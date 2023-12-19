package com.bookrental.bookrental.mapper;

import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select tc.id ,tc.\"name\" ,tc.description \n" +
            "           from tbl_category tc")
    List<CategoryResponsePojo> getAllCategory();
}
