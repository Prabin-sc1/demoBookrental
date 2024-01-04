package com.bookrental.bookrental.service.booktransaction;

import com.bookrental.bookrental.enums.RentType;
import com.bookrental.bookrental.exception.*;
import com.bookrental.bookrental.mapper.BookTransactionMapper;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.BookTransactionRepository;
import com.bookrental.bookrental.repository.MemberRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookTransactionServiceImpl implements BookTransactionService {

    private final BookTransactionRepository bookTransactionRepository;

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    private final BookTransactionMapper bookTransactionMapper;
    private final Random r = new Random();


    @Override
    public void addBookTransaction(BookRentRequest bookRentRequest) {
        BookTransaction bookTransaction = new BookTransaction();
        if (bookRentRequest.getId() != null)
            bookTransaction = bookTransactionRepository.findById(bookRentRequest.getId()).orElse(bookTransaction);

        try {
            beanUtils.copyProperties(bookTransaction, bookRentRequest);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }

        int tempMemberId = bookRentRequest.getMemberId();
        int tempBookId = bookRentRequest.getBookId();

        Book book = bookRepository.findById(tempBookId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", tempBookId));
        Member member = memberRepository.findById(tempMemberId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", tempMemberId));

        int overdewBooks = bookTransactionMapper.countTransactionsByMemberAndRentStatus(member.getId(), "RENT");
        if (overdewBooks > 0) {
            throw new MemberOverdewRentalException("Member has already rented this book, so can't rent another same book.");
        }

        bookTransaction.setFromDate(LocalDate.now());
        bookTransaction.setToDate(LocalDate.now().plusDays(10));
        bookTransaction.setRentStatus(RentType.RENT);
        bookTransaction.setMember(member);
        bookTransaction.setBook(book);
        bookTransaction.setCode("#"+r.nextInt());
        book.setStockCount(book.getStockCount() - 1);
        bookTransaction.setActiveClosed(true);

        if (book.getStockCount() < 1)
            throw new BookStockException("Sorry, we are out of stock!");
        else
            bookRepository.save(book);
        bookTransactionRepository.save(bookTransaction);
    }

    @Override
    public void returnBookTransaction(@Valid @RequestBody BookReturnRequest bookReturnRequest) {
        BookTransaction bookTransaction = findBookTransaction(bookReturnRequest);
        updateBookStockCount(bookReturnRequest.getBookId());
        updateBookTransaction(bookTransaction);
    }

    private BookTransaction findBookTransaction(BookReturnRequest bookReturnRequest) {
        return bookTransactionRepository.findByCodeAndMemberIdAndBookId(
                bookReturnRequest.getCode(),
                bookReturnRequest.getMemberId(),
                bookReturnRequest.getBookId()
        ).orElseThrow(() -> new InvalidTransactionStateException("Invalid code, member ID, or book ID"));
    }

    private void updateBookStockCount(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", id));
        book.setStockCount(book.getStockCount() + 1);
        bookRepository.save(book);
    }

    private void updateBookTransaction(BookTransaction bookTransaction) {
        bookTransaction.setToDate(LocalDate.now());
        bookTransaction.setRentStatus(RentType.RETURN);
        bookTransaction.setActiveClosed(true);
        bookTransactionRepository.save(bookTransaction);
    }

    @Override
    public List<BookTransactionResponse> getAllTransaction() {
        return bookTransactionMapper.getAll();
    }

    @Override
    public BookTransactionResponse getSingleTransactionById(Integer id) {
        return bookTransactionMapper.getById(id);
    }

    @Override
    public void deleteTransactionById(Integer id) {
        bookTransactionRepository.deleteById(id);
    }

    @Override
    public List<BookTransactionResponse> getAllTransactionByMember(Integer id) {
        return bookTransactionMapper.getAllTransactionByMemberId(id);
    }
}
