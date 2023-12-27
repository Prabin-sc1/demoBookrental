package com.bookrental.bookrental.mapper;

import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookTransactionMapper {
    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate, tbt.book_id as bookId, tbt.member_id as memberId, tbt.rent_status as rentStatus from tbl_book_transaction tbt")
    List<BookTransactionResponse> getAll();

    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate, tbt.book_id as bookId, tbt.member_id as memberId, " +
            "tbt.rent_status as rentStatus from tbl_book_transaction tbt where id = #{id}")
    BookTransactionResponse getById(@Param("id") Integer id);

    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate, tbt.book_id as bookId, tbt.member_id as memberId, " +
            "tbt.rent_status as rentStatus from tbl_book_transaction tbt where member_id = #{id}")
    List<BookTransactionResponse> getAllTransactionByMemberId(@Param("id") Integer id);
}

