package com.bookrental.bookrental.service.book;

import com.bookrental.bookrental.Exception.AppException;
import com.bookrental.bookrental.Exception.ResourceNotFoundException;
import com.bookrental.bookrental.config.CustomMessageSource;
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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final NullAwareBeanUtilsBean utilsBean = new NullAwareBeanUtilsBean();
    private CustomMessageSource customMessageSource;

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

        //get category id
        Integer cidd = book.getCategoryId();
        Category cc = this.categoryRepository.findById(cidd).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", cidd));

        authors = book.getAuthorId().stream().map(e -> authorRepository.findById(e).orElseThrow(() -> new ResourceNotFoundException("Author", "Id", 1))).collect(Collectors.toList());
        b.setAuthors(authors);
        b.setCategory(cc);
        bookRepository.save(b);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", id));
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponsePojo> getAllBooks() {
        return bookMapper.getAllBook();
    }

    @Override
    public BookResponsePojo getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }
}