package com.bookrental.bookrental.mapper;

import com.bookrental.bookrental.pojo.book.BookResponsePojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("SELECT a.* FROM tbl_author a INNER JOIN tbl_book_author ba ON a.id = ba.author_id WHERE ba.book_id = #{bookId}")
    List<Object> getAuthorsByBookId();

    @Results(id = "bookResultMap",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "noOfPages", column = "no_of_pages"),
                    @Result(property = "isbn", column = "isbn"),
                    @Result(property = "rating", column = "rating"),
                    @Result(property = "stockCount", column = "stock_count"),
                    @Result(property = "publishedDate", column = "published_date"),
                    @Result(property = "photo", column = "photo"),
                    @Result(property = "categoryId", column = "category_id"),
                    @Result(property = "authors", column = "id",
                            many = @Many(select = "getAuthorsByBookId"))
            })
    @Select("SELECT * FROM tbl_book where is_active")
    List<BookResponsePojo> getAllBook();

    @Results(id = "bookResultMap2",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "noOfPages", column = "no_of_pages"),
                    @Result(property = "isbn", column = "isbn"),
                    @Result(property = "rating", column = "rating"),
                    @Result(property = "stockCount", column = "stock_count"),
                    @Result(property = "publishedDate", column = "published_date"),
                    @Result(property = "photo", column = "photo"),
                    @Result(property = "categoryId", column = "category_id"),
                    @Result(property = "authors", column = "id",
                            many = @Many(select = "getAuthorsByBookId"))
            })
    @Select("SELECT * FROM tbl_book where id =#{id} and is_active")
    BookResponsePojo getBookById(@Param("id") Integer id);
}
