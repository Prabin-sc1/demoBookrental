package com.bookrental.bookrental.service.booktransaction;

import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.exception.BookStockException;
import com.bookrental.bookrental.exception.MemberOutstandingRentalsException;
import com.bookrental.bookrental.exception.ResourceNotFoundException;
import com.bookrental.bookrental.enums.RentType;
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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookTransactionServiceImpl implements BookTransactionService {

    private final BookTransactionRepository bookTransactionRepository;

    private final ModelMapper modelMapper;

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    private final BookTransactionMapper bookTransactionMapper;


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

        int a = bookRentRequest.getMemberId();
        int b = bookRentRequest.getBookId();


        Book book = bookRepository.findById(b).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", b));
        Member member = memberRepository.findById(a).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", a));

        List<BookTransaction> outstandingResults = bookTransactionMapper.findTransactionByMemberAndRestStatus(member.getId(), "RENT");
        if (!outstandingResults.isEmpty()) {
            throw new MemberOutstandingRentalsException("Member has already rented this book, so can't rent another same book.");
        }

        bookTransaction.setFromDate(LocalDate.now());
        bookTransaction.setToDate(LocalDate.now().plusDays(10));
        bookTransaction.setRentStatus(RentType.RENT);
        bookTransaction.setMember(member);
        bookTransaction.setBook(book);
        bookTransaction.setCode("#" + a + "RENT" + b);

        Book book1 = bookRepository.findById(b).get();

        book1.setStockCount(book1.getStockCount() - 1);
        bookTransaction.setActiveClosed(true);

        if (book1.getStockCount() < 1)
            throw new BookStockException("Sorry, we are out of stock!");
        else
            bookRepository.save(book1);
        bookTransactionRepository.save(bookTransaction);
    }

    @Override
    public void returnBookTransaction(BookReturnRequest bookReturnRequest) {
        BookTransaction returnBookTransaction = new BookTransaction();
        if (bookReturnRequest.getId() != null && bookReturnRequest.getCode() != null && bookReturnRequest.getMemberId() != null) {
            returnBookTransaction = bookTransactionRepository.findById(bookReturnRequest.getId()).orElse(returnBookTransaction);
        }

        try {
            beanUtils.copyProperties(returnBookTransaction, bookReturnRequest);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }

        int memberId = bookReturnRequest.getMemberId();
        int bookId = bookReturnRequest.getBookId();

        Member m = memberRepository.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("Member", "Id", memberId));
        Book b = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));

        returnBookTransaction.setRentStatus(RentType.RETURN);
        returnBookTransaction.setMember(m);
        returnBookTransaction.setBook(b);
        Book book = bookRepository.findById(bookId).get();
        book.setStockCount(b.getStockCount() + 1);
        bookRepository.save(book);
        returnBookTransaction.setActiveClosed(false);
        bookTransactionMapper.update(memberId);
        bookTransactionRepository.save(returnBookTransaction);
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
