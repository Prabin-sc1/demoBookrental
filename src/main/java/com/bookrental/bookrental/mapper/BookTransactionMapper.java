package com.bookrental.bookrental.mapper;

import com.bookrental.bookrental.model.BookTransaction;
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

    @Select("SELECT COUNT(*) FROM tbl_book_transaction WHERE member_id = #{id} AND rent_status = #{rentStatus}")
    int countTransactionsByMemberAndRentStatus(@Param("id") int memberId, @Param("rentStatus") String rentStatus);

    @Select("SELECT\n" +
            "    tbt.id,\n" +
            "    tbt.code,\n" +
            "    tbt.from_date,\n" +
            "    tbt.to_date,\n" +
            "    tbt.book_id AS bookId,\n" +
            "    tbt.member_id AS memberId,\n" +
            "    tbt.rent_status AS rentStatus\n" +
            "FROM\n" +
            "    tbl_book_transaction tbt\n" +
            "WHERE\n" +
            "    tbt.to_date < CURRENT_DATE and rent_status ='RENT'\n" +
            "ORDER BY\n" +
            "    from_date")
    List<BookTransactionResponse> overdeuList();
}

