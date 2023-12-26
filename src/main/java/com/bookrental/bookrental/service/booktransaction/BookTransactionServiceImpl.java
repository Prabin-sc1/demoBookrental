package com.bookrental.bookrental.service.booktransaction;

import com.bookrental.bookrental.Exception.AppException;
import com.bookrental.bookrental.Exception.ResourceNotFoundException;
import com.bookrental.bookrental.enums.RentType;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnResponse;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.BookTransactionRepository;
import com.bookrental.bookrental.repository.MemberRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookTransactionServiceImpl implements BookTransactionService {

    private final BookTransactionRepository bookTransactionRepository;

    private final ModelMapper modelMapper;

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    @Override
    public void addBookTransaction(BookRentRequest bookRentRequest) {
        BookTransaction bookTransaction = new BookTransaction();
        if (bookRentRequest.getId() != null) {
            bookTransaction = bookTransactionRepository.findById(bookRentRequest.getId()).orElse(bookTransaction);
        }
        try {
            beanUtils.copyProperties(bookTransaction, bookRentRequest);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        int a = bookRentRequest.getMemberId();

        int b = bookRentRequest.getBookId();
        Book book = bookRepository.findById(b).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", b));
        Member member = memberRepository.findById(a).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", a));
        bookTransaction.setRentStatus(RentType.RENT);
        bookTransaction.setMember(member);
        bookTransaction.setBook(book);
        Book book1 = this.bookRepository.findById(b).get();
        int aa = book1.getStockCount();
        System.out.println(aa);
        book1.setStockCount(book1.getStockCount() - 1);
        int bb = book1.getStockCount();
        System.out.println(bb);

        if (book1.getStockCount() < 1) {
//            System.out.println("book stack is empty");

        } else {
            bookRepository.save(book1);
        }
        bookTransactionRepository.save(bookTransaction);
    }

    @Override
    public BookReturnResponse returnBookTransaction(BookReturnRequest bookReturnRequest) {
        return null;
    }

    @Override
    public List<BookTransactionResponse> getAllTransaction() {
        return null;
    }
}
