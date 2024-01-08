package com.bookrental.bookrental.service.book;

import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.exception.BookAlreadyExistsException;
import com.bookrental.bookrental.exception.ResourceNotFoundException;
import com.bookrental.bookrental.mapper.BookMapper;
import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Category;
import com.bookrental.bookrental.pojo.book.BookRequestPojo;
import com.bookrental.bookrental.pojo.book.BookResponsePojo;
import com.bookrental.bookrental.repository.AuthorRepository;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.CategoryRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final NullAwareBeanUtilsBean utilsBean = new NullAwareBeanUtilsBean();

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public void createUpdateBook(BookRequestPojo book) {
        List<Author> authors;
        Book b = new Book();

        if (book.getId() != null) {
            b = bookRepository.findById(book.getId()).orElse(b);
        }

        try {
            utilsBean.copyProperties(b, book);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }

        Integer categoryId = book.getCategoryId();
        Category tempCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        authors = book.getAuthorId().stream().map(e -> authorRepository.findById(e).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", 1))).toList();
        b.setAuthors(authors);
        b.setCategory(tempCategory);
        try {
            bookRepository.save(b);
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistsException("The name of this book, " + book.getName() + " already exists.");
        }
    }

    @Override
    public List<BookResponsePojo> getAllBooks() {
        return bookMapper.getAllBook();
    }

    @Override
    public BookResponsePojo getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}