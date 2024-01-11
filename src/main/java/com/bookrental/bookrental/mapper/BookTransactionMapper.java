package com.bookrental.bookrental.mapper;

import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookTransactionMapper {
//    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate, tbt.book_id as bookId, tbt.member_id as memberId, tbt.rent_status as rentStatus from tbl_book_transaction tbt")
//    List<BookTransactionResponse> getAll();

    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate,tbt.rent_status as rentStatus, tb.\"name\" as bookName, tm.\"name\" as memberName  from tbl_book_transaction tbt\n" +
            "      join tbl_book tb on tbt.book_id = tb.id  join tbl_member tm on tm.id = tbt.member_id")
    List<BookTransactionResponse> getAll();

    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate, tbt.book_id as bookId, tbt.member_id as memberId, " +
            "tbt.rent_status as rentStatus from tbl_book_transaction tbt where id = #{id}")
    BookTransactionResponse getById(@Param("id") Integer id);

    @Select("select tbt.id ,tbt.code ,tbt.from_date as fromDate, tbt.to_date as toDate, tbt.book_id as bookId, tbt.member_id as memberId, " +
            "tbt.rent_status as rentStatus from tbl_book_transaction tbt where member_id = #{id}")
    List<BookTransactionResponse> getAllTransactionByMemberId(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM tbl_book_transaction WHERE member_id = #{id} AND rent_status = #{rentStatus}")
    int countTransactionsByMemberAndRentStatus(@Param("id") int memberId, @Param("rentStatus") String rentStatus);

    @Select("""
            SELECT
                tbt.id,
                tbt.code,
                tbt.from_date,
                tbt.to_date,
                tbt.book_id AS bookId,
                tbt.member_id AS memberId,
                tbt.rent_status AS rentStatus
            FROM
                tbl_book_transaction tbt
            WHERE
                tbt.to_date < CURRENT_DATE and rent_status ='RENT'
            ORDER BY
                from_date""")
    List<BookTransactionResponse> overdeuList();
}

