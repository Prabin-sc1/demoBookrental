package com.bookrental.bookrental.mapper;

import com.bookrental.bookrental.pojo.category.CategoryResponsePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select tc.id ,tc.\"name\" ,tc.description from tbl_category tc where is_active")
    List<CategoryResponsePojo> getAllCategory();

    @Select("select tc.id ,tc.\"name\" ,tc.description \n" +
            "           from tbl_category tc where tc.is_active and tc.id = #{id}")
    CategoryResponsePojo getSingleCategory(@Param("id") Integer id);
}
