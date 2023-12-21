package com.bookrental.bookrental.service.book;

import com.bookrental.bookrental.Exception.AppException;
import com.bookrental.bookrental.Exception.ResourceNotFoundException;
import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.book.BookRequestPojo;
import com.bookrental.bookrental.repository.AuthorRepository;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final NullAwareBeanUtilsBean utilsBean = new NullAwareBeanUtilsBean();
    private CustomMessageSource customMessageSource;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public void createUpdateBook(BookRequestPojo book, Integer cid) {
//        Author a = this.authorRepository.findById(aid).orElseThrow(() -> new ResourceNotFoundException("Author","Id",aid));
        Category c = this.categoryRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", cid));
        Book b = new Book();

        if (book.getId() != null) {
            b = bookRepository.findById(book.getId()).orElse(b);
        }

        try {
            utilsBean.copyProperties(b, book);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage(), e);
        }
//        b.setAuthors(null);
        b.setCategory(c);
        bookRepository.save(b);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.BOOK)));
    }

    @Override
    public List<Book> getAllBookByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Book> getAllBookByAuthor(Integer authorId) {
        return null;
    }
}
