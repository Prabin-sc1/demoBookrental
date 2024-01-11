package com.bookrental.bookrental.service.author;

import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.exception.CategoryAlreadyExistsException;
import com.bookrental.bookrental.mapper.AuthorMapper;
import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;
import com.bookrental.bookrental.pojo.author.AuthorResponsePojo;
import com.bookrental.bookrental.repository.AuthorRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    private final AuthorMapper authorMapper;

    @Override
    public void createUpdateAuthor(AuthorRequestPojo authorRequestPojo) {
        Author author = new Author();
        if (authorRequestPojo.getId() != null) {
            author = authorRepository.findById(authorRequestPojo.getId()).orElse(author);
        }

        try {
            beanUtils.copyProperties(author, authorRequestPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }
        try {
            authorRepository.save(author);
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("A author with the email " + author.getEmail() + " already exists.");
        }
    }

    @Override
    public List<AuthorResponsePojo> getAllAuthor() {
//        List<Author> a = authorRepository.findAll();
//        List<AuthorResponsePojo> list1 = a.stream().map(e -> this.modelMapper.map(e, AuthorResponsePojo.class)).collect(Collectors.toList());
//        return list1;
        return authorMapper.getAllAuthor();
    }

    @Override
    public AuthorResponsePojo getAuthorById(Integer id) {
        // previously I used to write code this way
//        Author a = authorRepository.findById(id).orElseThrow(() -> new AppException(customMessageSource.
//                get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.AUTHOR)));
//        return this.modelMapper.map(a, AuthorResponsePojo.class);
        // this is the latest one to do same thing
        return authorMapper.getSingleAuthor(id);
    }

    @Override
    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }
}